package com.rueggerllc.tasks;

import android.os.AsyncTask;

import com.rueggerllc.activities.AddBookActivity;
import com.rueggerllc.client.ClientFactory;
import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.client.BookClient;
import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.util.Constants;

/**
 * Generics Parameters:
 * AsyncTask<A, B, C>
 * doInBackground(A Vararg)
 * onProgressUpdate(B Vararg)
 * C doInBackground(), onPostExecute(C)
 */
public class AsyncTaskAddBook extends AsyncTask<String, Integer, Book> {

        private Logger logger = new Logger(Constants.APPLICATION_NAME);
        private AddBookActivity addBookActivity;
        private Book bookToAdd;

        public AsyncTaskAddBook(AddBookActivity addBookActivity, Book bookToAdd) {
            this.addBookActivity = addBookActivity;
            this.bookToAdd = bookToAdd;
        }

        @Override
        protected void onPreExecute() {
            logger.debug("On PreExecute()");
        }


        @Override
        protected Book doInBackground(String...urls) {
            logger.debug("AsyncTaskAddBook doInBackground: " + urls[0]);
            logger.debug("Connecting to Web Service....");
            logger.debug("Endpoint to use=" + urls[0]);
            ClientFactory clientFactory = ClientFactory.getInstance();
            BookClient bookClient = clientFactory.getBookClient(urls[0]);
            logger.info("ENDPOINT=" + bookClient.getEndpoint());
            Book book = bookClient.addBook(bookToAdd);
            logger.debug("Added BOOK=\n" + book);
            return book;

        }

        @Override
        protected void onProgressUpdate(Integer...progress) {
            logger.debug("Progress Update:" + progress[0]);
        }

        @Override
        protected void onPostExecute(Book result) {
            logger.debug("onPostExecute()");
            logger.debug("Book Added");
            addBookActivity.gotoListBooks();
        }


}

