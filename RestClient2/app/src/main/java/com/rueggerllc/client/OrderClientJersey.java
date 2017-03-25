package com.rueggerllc.client;

import android.util.Log;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import org.json.JSONObject;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class OrderClientJersey {

    private static String orderServiceURI = "http://rueggerconsultingllc.com/RestWeb/rest/orders";
    private static String bookServiceURI = "http://rueggerconsultingllc.com/RestWeb/rest/books";

    public String requestContent(String url) {
        Log.d("RestClient", "CHRIS GETTING DATA WITH JERSEY CLIENT");
        ClientConfig config = new DefaultClientConfig();
        // config.getClasses().add(com.sun.jersey.impl.provider.entity.JSONRootElementProvider.class);
        Log.d("RestClient", "GOT CONFIG");

        Client client = Client.create(config);
        // Client client = Client.create();
        Log.d("RestClient", "GOT CLIENT");

        // URI getURI = getBookTargetURI("book/id/1");
        URI getURI = getBookTargetURI("books");
        String response = client.resource(getURI).accept("application/json").get(String.class);
        Log.d("RestClient", response);
        Log.d("RestClient", "============== CHRIS GOT THE RESPONSE");

        try {
            Log.d("RestClient", "PARSING RESPONSE BEGIN GET QTY");
            // JSONObject jsonObject = new JSONObject(response);

            // String title = jsonObject.getString("title");
            // Log.d("RestClient", "title=" + title);

            // String quantity = jsonObject.getString("quantity");
            // Log.d("RestClient", "quantity=" + quantity);

            // String confirmationNumber = jsonObject.getString("confirmationNumber");
            // Log.d("RestClient", "confirmationNumber=" + confirmationNumber);



            Log.d("RestClient", "GOT OBJECT");
        } catch (Exception e) {
            Log.d("RestClient", "Error Parsing JSON Response:" + e);
        }
        return response;
    }

    private URI getOrderTargetURI(String path) {
        // String orderURIValue = String.format("http://localhost:9080/RestWAR/rest/orders/%s", path);
        String orderURIValue = String.format("%s/%s", orderServiceURI, path);
        Log.d("RestClient", "Get Target URI");
        URI orderURI = UriBuilder.fromUri(orderURIValue).build();
        return orderURI;
    }

    private URI getBookTargetURI(String path) {
        String orderURIValue = String.format("%s/%s", bookServiceURI, path);
        Log.d("RestClient", "Get Target URI");
        URI orderURI = UriBuilder.fromUri(orderURIValue).build();
        return orderURI;
    }

}
