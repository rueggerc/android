package com.rueggerllc.restlib;


import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.client.BookClient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class BookClientTest {

    private static final String APPLICATION = "RestClient2";

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
        List<Book> books = bookClient.getBooks();
        for (Book book : books) {
            System.out.println("Next book=\n" + book);
        }

    }
}
