package com.rueggerllc.tests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rueggerllc.restclient.BookClient;

public class BookClientTest {
	
	private static Logger logger = Logger.getLogger(BookClientTest.class);
	
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
		logger.info("Inside Dummy Test");
	}
	

	@Test
	// @Ignore
	public void testGetBooks() {
		try {
			logger.info("--- Get Books Begin ---");
			BookClient bookClient = new BookClient();
			bookClient.getBooks();
		} catch (Exception e) {
			logger.info("ERROR", e);
		}
	}
	

}
