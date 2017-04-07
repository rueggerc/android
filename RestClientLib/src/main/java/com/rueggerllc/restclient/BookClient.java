package com.rueggerllc.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class BookClient {
	
	private static final Logger logger = Logger.getLogger(BookClient.class);
	
	
	public void getBooksJersey() {
		logger.info("getBooks BEGIN");
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		URI targetURI = getTargetURI("books");
		// Books theBooks = client.resource(targetURI).get(Books.class);
		// logger.info("Retrieved Books: " + theBooks.size());
	}
	
	public void getBooks() {
		try {
			logger.info("getBooks BEGIN");
			String url = "http://rueggerconsultingllc.com/RestWeb/rest/books/books";
			
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			request.addHeader("Accept", "application/json");
			HttpResponse response = client.execute(request);
			
			int statusCode = response.getStatusLine().getStatusCode();
			logger.info("Status code=" + statusCode);
			if (statusCode != 200) {
				return;
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer buffer = new StringBuffer();
	        String line = "";
	        while ((line = reader.readLine()) != null) {
	        	buffer.append(line);
	        }
	        logger.info(buffer.toString());
			JSONArray books = new JSONArray(buffer.toString());
			for (int i = 0; i < books.length(); i++) {
				JSONObject book = books.getJSONObject(i);
				String title = book.getString("title");
				logger.info("Next Book=" + title);
				
				
				
			}
	     
			
		} catch (Exception e) {
			logger.error("Error", e);
		}
	}
	
	
	private URI getTargetURI(String path) {
		String targetURIValue = String.format("http://darwin:8080/RestWeb/rest/books/%s/", path);
		URI targetURI = UriBuilder.fromUri(targetURIValue).build();
		return targetURI;
	}

}
