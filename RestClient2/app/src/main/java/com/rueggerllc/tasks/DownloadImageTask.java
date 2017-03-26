package com.rueggerllc.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.rueggerllc.client.BookClient;
import com.rueggerllc.activities.MainActivity;
import com.rueggerllc.util.Constants;
import com.rueggerllc.util.Logger;

import java.io.InputStream;

/**
 * Generics Parameters:
 * doInBackground(Parameters Vararg)
 * onProgressUpdate(Paramters Vararg)
 * Result for doInBackground(), InputParm to onPostExecute()
 */
public class DownloadImageTask extends AsyncTask<String,Integer,Bitmap> {

        private Logger logger = new Logger();
        private MainActivity mainActivity;

        public DownloadImageTask(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        protected void onPreExecute() {
            logger.debug("On PreExecute()");
        }


        @Override
        protected Bitmap doInBackground(String...urls) {
            String imageURL = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer...progress) {
            logger.debug("Progress Update:" + progress[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            logger.debug("DownloadImageTask onPostExecute()");
            mainActivity.setImage(result);

        }





}

