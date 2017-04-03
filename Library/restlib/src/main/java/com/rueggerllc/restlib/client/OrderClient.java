package com.rueggerllc.restlib.client;


import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.beans.Order;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderClient {

    private String application;

    public OrderClient(String application) {
        this.application = application;
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try {

            System.out.println("===GET ORDERS BEGIN===");
            OkHttpClient client = new OkHttpClient();

            String url = "http://rueggerconsultingllc.com/RestWeb/rest/orders/orders";

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
            System.out.println("Response=\n" + responseValue);

            JSONArray orderValues = new JSONArray(responseValue);
            for (int i = 0; i < orderValues.length(); i++) {
                JSONObject orderValue = orderValues.getJSONObject(i);
                int id = orderValue.getInt("id");
                String itemNumber = orderValue.getString("itemNumber");
                int quantity = orderValue.getInt("quantity");
                String confirmationNumber = orderValue.getString("confirmationNumber");
                Order order = new Order();
                order.setId(id);
                order.setItemNumber(itemNumber);
                order.setQuantity(quantity);
                order.setConfirmationNumber(confirmationNumber);
                orders.add(order);


                // long publicationDateMilliseconds = bookValue.getLong("publicationDate");
                // Calendar calendar = Calendar.getInstance();
                // calendar.setTimeInMillis(publicationDateMilliseconds);
                // Date publicationDate = calendar.getTime();
                // Book book = new Book();
                // book.setTitle(title);
                // book.setNumberOfPages(numberOfPages);
                // book.setPublicationDate(publicationDate);
                // books.add(book);
            }




        } catch (Exception e) {
            System.out.println("ERROR\n" + e);
        }

        // Done
        return orders;
    }

    public Order getOrder(int id) {
        return null;
    }

    public void foo() {
    }

    public void bar() {
    }

}
