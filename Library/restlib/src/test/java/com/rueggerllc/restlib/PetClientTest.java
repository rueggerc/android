package com.rueggerllc.restlib;


import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.beans.Pet;
import com.rueggerllc.restlib.client.BookClient;
import com.rueggerllc.restlib.client.PetClient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class PetClientTest {

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
    public void testGetPets() {
        System.out.println("Get Pets Begin");
        PetClient petClient = new PetClient(APPLICATION);
        List<Pet> pets = petClient.getPets();
        for (Pet pet : pets) {
            System.out.println("Next pet=\n" + pet);
        }

    }
}
