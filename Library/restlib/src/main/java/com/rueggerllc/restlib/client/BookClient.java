package com.rueggerllc.restlib.client;


import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.util.Logger;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookClient {

    private String application;
    private Logger logger;

    public BookClient(String application) {
        this.application = application;
        this.logger = new Logger(application);
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {

            System.out.println("===GET BOOKS BEGIN===");
            OkHttpClient client = new OkHttpClient();

            String url = "http://rueggerconsultingllc.com/RestWeb/rest/books/books";

            Request request = new Request.Builder()
                    .header("Accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful() == false) {
                int responseCode = response.code();
                throw new Exception("Call Failed!!" + responseCode);
            }

            String responseValue = response.body().string();
            System.out.println("JSON=\n" + responseValue);

            JSONArray bookValues = new JSONArray(responseValue);
            for (int i = 0; i < bookValues.length(); i++) {
                JSONObject bookValue = bookValues.getJSONObject(i);
                String title = bookValue.getString("title");
                int numberOfPages = bookValue.getInt("numberOfPages");
                long publicationDateMilliseconds = bookValue.getLong("publicationDate");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(publicationDateMilliseconds);
                Date publicationDate = calendar.getTime();
                Book book = new Book();
                book.setTitle(title);
                book.setNumberOfPages(numberOfPages);
                book.setPublicationDate(publicationDate);
                books.add(book);
            }



        } catch (Exception e) {
            System.out.println("ERROR\n" + e);
        }

        // Done
        return books;

    }
}
