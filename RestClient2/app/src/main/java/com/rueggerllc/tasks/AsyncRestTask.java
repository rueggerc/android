package com.rueggerllc.tasks;

import android.os.AsyncTask;
import android.util.Log;



import com.rueggerllc.client.ClientFactory;
import com.rueggerllc.activities.MainActivity;
import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.beans.Order;
import com.rueggerllc.restlib.client.BookClient;
import com.rueggerllc.restlib.client.OrderClient;
import com.rueggerllc.util.Constants;
import com.rueggerllc.util.Logger;



import java.util.List;

/**
 * Generics Parameters:
 * AsyncTask<A, B, C>
 * doInBackground(A Vararg)
 * onProgressUpdate(B Vararg)
 * C doInBackground(), onPostExecute(C)
 */
public class AsyncRestTask extends AsyncTask<String, Integer, List<Book>> {

        private Logger logger = new Logger();
        private MainActivity mainActivity;

        public AsyncRestTask(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        protected void onPreExecute() {
            logger.debug("On PreExecute()");
        }


        @Override
        protected List<Book> doInBackground(String...urls) {
            Log.d("RestClient", "Async REST Task: " + urls[0]);
            Log.d("RestClient", "Connecting to Web Service!");
            ClientFactory clientFactory = ClientFactory.getInstance();
            BookClient bookClient = clientFactory.getBookClient();

            List<Book> result = bookClient.getBooks();
            Log.d("RestClient", "Results=\n" + result);
            publishProgress(100);

            getOrders();

            if (result == null) {
                return null;
            } else {
                // parseResponse(result);
                return result;
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

            orderClient.foo();
            orderClient.bar();



        }




}

