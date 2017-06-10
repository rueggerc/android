package com.rueggerllc.tasks;

import android.os.AsyncTask;

import com.rueggerllc.activities.ListBooksActivity;
import com.rueggerllc.client.ClientFactory;
import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.client.BookClient;
import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.util.Constants;

import java.util.List;

/**
 * Generics Parameters:
 * AsyncTask<A, B, C>
 * doInBackground(A Vararg)
 * onProgressUpdate(B Vararg)
 * C doInBackground(), onPostExecute(C)
 */
public class AsyncTaskBookClient extends AsyncTask<String, Integer, List<Book>> {

        private Logger logger = new Logger(Constants.APPLICATION_NAME);
        private ListBooksActivity listBooksActivity;

        public AsyncTaskBookClient(ListBooksActivity listBooksActivity) {
            this.listBooksActivity = listBooksActivity;
        }

        @Override
        protected void onPreExecute() {
            logger.debug("On PreExecute()");
        }


        @Override
        protected List<Book> doInBackground(String...urls) {
            logger.debug("AsyncTaskBookClient: " + urls[0]);
            logger.debug("Connecting to Web Service!");
            logger.debug("Endpoint to use=" + urls[0]);
            ClientFactory clientFactory = ClientFactory.getInstance();
            BookClient bookClient = clientFactory.getBookClient(urls[0]);

            List<Book> books = bookClient.getBooks();
            publishProgress(100);

            if (books == null) {
                return null;
            } else {
                return books;
            }

        }

        @Override
        protected void onProgressUpdate(Integer...progress) {
            logger.debug("Progress Update:" + progress[0]);
        }

        @Override
        protected void onPostExecute(List<Book> result) {
            logger.debug("onPostExecute()");
            if (result == null) {
                listBooksActivity.buildError();
            } else {
                logger.debug(result.size() + " Books Were Read");
                listBooksActivity.buildList(result);
            }

        }


}

