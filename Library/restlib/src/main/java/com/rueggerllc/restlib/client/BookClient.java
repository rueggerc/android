package com.rueggerllc.restlib.client;


import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.util.Logger;
import com.rueggerllc.restlib.util.Utils;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
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
    private String endpoint = "http://rueggerconsultingllc.com/RestWeb/rest/";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public BookClient(String application) {
        this.application = application;
        this.logger = new Logger(application);
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {

            System.out.println("===GET BOOKS BEGIN===");
            OkHttpClient client = new OkHttpClient();

            // String url = "http://rueggerconsultingllc.com/RestWeb/rest/books/books";
            // String url = "http://localhost:8080/RestWeb/rest/books/books";
            // String url = "http://localhost:8080/RestWeb/rest/books/books";
            String targetURL = getTargetURL("books/books");

            Request request = new Request.Builder()
                    .header("Accept", "application/json")
                    .url(targetURL)
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
                Book book = getBook(bookValue);
                books.add(book);
            }



        } catch (Exception e) {
            System.out.println("ERROR\n" + e);
        }

        // Done
        return books;

    }



    public  Book getBook(String id) {
        try {
            // logger.info("--- Here we go Get a Book ----");
            OkHttpClient client = new OkHttpClient();

            String url = String.format("http://localhost:8080/RestWeb/rest/books/book/id/%s", id);
            // String url = String.format("http://rueggerconsultingllc.com/RestWeb/rest/books/book/id/%s", id);
            Request request = new Request.Builder()
                    .header("Accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful() == false) {
                int responseCode = response.code();
                throw new Exception("Call Failed!!" + responseCode);
            }

            String responseData = response.body().string();
            System.out.println("JSON=\n" + responseData);
            if (!Utils.isBlank(responseData)) {
                return getBook(new JSONObject(responseData));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("ERROR\n" + e);
            return null;
        }
    }

    public  Book addBook(Book newBook) {
        try {
            // logger.info("--- Here we go Get a Book ----");
            OkHttpClient client = new OkHttpClient();


            String bookDataTemplate =
                    "{\"title\":\"%s\", \"numberOfPages\":%d, \"publicationDate\":%d}";
            String bookData = String.format(bookDataTemplate, newBook.getTitle(), newBook.getNumberOfPages(), newBook.getPublicationDate().getTime());

            String url = "http://localhost:8080/RestWeb/rest/books/book";
            RequestBody requestBody = RequestBody.create(JSON, bookData);
            Request request = new Request.Builder()
                    .header("Accept", "application/json")
                    .url(url)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful() == false) {
                int responseCode = response.code();
                throw new Exception("Call Failed!!" + responseCode);
            }

            String responseData = response.body().string();
            if (!Utils.isBlank(responseData)) {
                return getBook(new JSONObject(responseData));
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    private Book getBook(JSONObject bookValue) {
        try {
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
            return book;
        } catch (Exception e) {
            return null;
        }
    }

    private String getTargetURL(String path) {
        return endpoint + path;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

}
