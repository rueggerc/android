package com.rueggerllc.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.rueggerllc.client.BookClient;
import com.rueggerllc.client.OrderClient;
import com.rueggerllc.client.OrderClientJersey;
import com.rueggerllc.activities.MainActivity;
import com.rueggerllc.util.Constants;
import com.rueggerllc.util.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Generics Parameters:
 * doInBackground(Parameters Vararg)
 * onProgressUpdate(Parameters Vararg)
 * Result for doInBackground(), InputParm to onPostExecute()
 */
public class AsyncRestTask extends AsyncTask<String,Integer,String> {

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
        protected String doInBackground(String...urls) {
            Log.d("RestClient", "Async REST Task: " + urls[0]);
            Log.d("RestClient", "Connecting to Web Service!");
            BookClient client = new BookClient(Constants.GET_BOOKS_URL);
            String result = client.getBooks();
            Log.d("RestClient", "Results=\n" + result);
            publishProgress(100);
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
        protected void onPostExecute(String result) {
            logger.debug("onPostExecute()=" + result);
            if (result == null) {
                mainActivity.buildError();
            } else {
                mainActivity.buildList(result);
            }

        }



//        private void parseResponse(String response) {
//            try {
//                JSONArray books = new JSONArray(response);
//                for (int i = 0; i < books.length(); i++) {
//                    JSONObject book = books.getJSONObject(i);
//                    String title = book.getString("title");
//                    mainActivity.getBookList().add(title);
//                    logger.debug("Added=" + title);
//                }
//            } catch (Exception e) {
//
//            }
//        }



}

