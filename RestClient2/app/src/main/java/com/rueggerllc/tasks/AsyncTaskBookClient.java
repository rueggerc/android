package com.rueggerllc.tasks;

import android.os.AsyncTask;

import com.rueggerllc.activities.MainActivity;
import com.rueggerllc.client.ClientFactory;
import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.beans.Order;
import com.rueggerllc.restlib.client.BookClient;
import com.rueggerllc.restlib.client.OrderClient;
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
        private MainActivity mainActivity;

        public AsyncTaskBookClient(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        protected void onPreExecute() {
            logger.debug("On PreExecute()");
        }


        @Override
        protected List<Book> doInBackground(String...urls) {
            logger.debug("AsyncTaskBookClient: " + urls[0]);
            logger.debug("Connecting to Web Service!");
            ClientFactory clientFactory = ClientFactory.getInstance();
            BookClient bookClient = clientFactory.getBookClient();

            List<Book> books = bookClient.getBooks();
            logger.debug("Results=\n" + books);
            publishProgress(100);

            getOrders();

            if (books == null) {
                return null;
            } else {
                // parseResponse(result);
                return books;
            }

        }

        @Override
        protected void onProgressUpdate(Integer...progress) {
            logger.debug("Progress Update:" + progress[0]);
        }

        @Override
        protected void onPostExecute(List<Book> result) {
            logger.debug("onPostExecute()=" + result);
            if (result == null) {
                mainActivity.buildError();
            } else {
                mainActivity.buildList(result);
            }

        }

        private void getOrders() {
            logger.debug("====== GET ORDERS BEGIN =====");
            ClientFactory clientFactory = ClientFactory.getInstance();
            OrderClient orderClient = clientFactory.getOrderClient();
            BookClient bookClient = clientFactory.getBookClient();

            List<Order> orders = orderClient.getOrders();
            for (Order order : orders) {
                logger.debug("Next Order=\n" + order);
            }

            // orderClient.foo();
            // orderClient.bar();



        }




}

