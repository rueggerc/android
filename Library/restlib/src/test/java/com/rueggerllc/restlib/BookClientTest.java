package com.rueggerllc.restlib;


import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.client.BookClient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookClientTest {

    private static final String APPLICATION = "RestClient2";
    private String endpoint = "http://localhost:8080/RestWeb/rest/";

    @BeforeClass
    public static void setupClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setupTest() throws Exception {
    }

    @After
    public void tearDownTest() throws Exception {
    }

    @Test
    @Ignore
    public void dummyTest() {
        System.out.println("Inside Dummy Test");
    }


    @Test
    // @Ignore
    public void testGetBooks() {
        System.out.println("Get Books Begin");
        BookClient bookClient = new BookClient(APPLICATION);
        bookClient.setEndpoint(endpoint);
        List<Book> books = bookClient.getBooks();
        for (Book book : books) {
            System.out.println("Next book=\n" + book);
        }

    }

    @Test
    @Ignore
    public void testGetBook() {
        System.out.println("Get Book Begin");
        String id = "17";
        BookClient bookClient = new BookClient(APPLICATION);
        Book book = bookClient.getBook(id);
        System.out.println("Book=\n" + book);

    }

    @Test
    @Ignore
    public void addBook() {
        System.out.println("Add Book Begin");
        Book book = new Book();
        book.setTitle("Navy Federal Book 2");
        book.setNumberOfPages(42);
        book.setPublicationDate(getNow());
        BookClient bookClient = new BookClient(APPLICATION);
        Book addedBook = bookClient.addBook(book);
        System.out.println("Addedd Book=\n" + addedBook);

    }

    @Test
    @Ignore
    public void testTime() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        System.out.println("TIME=" + timeInMillis);

        Date now = getNow();
        System.out.println("Time=" + now.getTime());


    }




    private Date getNow() {
        return Calendar.getInstance().getTime();
    }
}
