package com.rueggerllc.client;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chris on 9/10/2016.
 */
public class OrderClient {

    // private static String bookServiceURI = "http://rueggerconsultingllc.com/RestWeb/rest/books";

    public String requestContent(String url) {
        HttpClient httpclient = new DefaultHttpClient();
        String result = null;
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = null;
        InputStream instream = null;

        try {
            Log.d("RestClient", "connecting to:" + url);
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                Log.d("RestClient", "Entity is not null!");
                instream = entity.getContent();
                result = convertStreamToString(instream);
            } else {
                Log.d("RestClient", "Entity IS null!");
            }

        } catch (Exception e) {
            // manage exceptions
            Log.d("RestClient", "Error\n", e);
        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (Exception exc) {

                }
            }
        }

        return result;
    }


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }

        return sb.toString();
    }

}
