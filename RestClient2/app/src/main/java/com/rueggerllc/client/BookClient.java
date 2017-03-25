package com.rueggerllc.client;

import com.rueggerllc.util.Constants;
import com.rueggerllc.util.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BookClient {

    private static Logger logger = new Logger();
    private static String bookServiceURI = "http://rueggerconsultingllc.com/RestWeb/rest/books/books";
    private String url;

    public BookClient(String url) {
        this.url = url;
    }

    public String getBooks() {
        String result = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // logger.info("connecting to:" + url);
            logger.debug("BookClient.newWay2 BEGIN");
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            request.addHeader("Accept", "application/json");
            HttpResponse response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            logger.debug("StatusCode=" + statusCode);
            if (statusCode != Constants.GET_SUCCESS) {
                logger.debug("Error Calling Web Service");
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }


        } catch (Exception e) {
            logger.debug("Error\n" + e);
        } finally {
            close(reader);
        }

        return buffer.toString();
    }

    private void close(BufferedReader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (Exception e) {
        }
    }


}
