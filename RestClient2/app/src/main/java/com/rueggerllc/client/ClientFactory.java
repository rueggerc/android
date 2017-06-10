package com.rueggerllc.client;


import com.rueggerllc.restlib.client.BookClient;
import com.rueggerllc.restlib.client.OrderClient;

public class ClientFactory {


    private static ClientFactory instance;
    private static final String APPLICATION = "RestClient2";
    private OrderClient orderClient;
    private BookClient bookClient;

    private ClientFactory() {
    }

    public static ClientFactory getInstance() {
        if (instance == null) {
            instance = new ClientFactory();
            return instance;
        }
        return instance;
    }

    public OrderClient getOrderClient() {
        if (orderClient == null) {
            orderClient = new OrderClient(APPLICATION);
        }
        return orderClient;
    }

    public BookClient getBookClient(String endpoint) {
        if (bookClient == null) {
            bookClient = new BookClient(APPLICATION);
        }
        if (endpoint != null) {
            bookClient.setEndpoint(endpoint);
        }
        return bookClient;
    }

}
