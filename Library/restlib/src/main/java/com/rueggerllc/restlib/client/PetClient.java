package com.rueggerllc.restlib.client;


import com.rueggerllc.restlib.beans.Book;
import com.rueggerllc.restlib.beans.Pet;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PetClient {

    private String application;

    public PetClient(String application) {
        this.application = application;
    }

    public List<Pet> getBooks() {
        List<Pet> pets = new ArrayList<>();
        try {

            OkHttpClient client = new OkHttpClient();

            String url = "http://rueggerconsultingllc.com/RestWeb/rest/pets/pets";

            Request request = new Request.Builder()
                    // .header("Accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful() == false) {
                int responseCode = response.code();
                throw new Exception("Call Failed!!" + responseCode);
            }

            String responseValue = response.body().string();
            JSONArray petValues = new JSONArray(responseValue);
            for (int i = 0; i < petValues.length(); i++) {
                JSONObject petValue = petValues.getJSONObject(i);
                Pet pet = new Pet();

                String name = petValue.getString("name");
                pet.setName(name);
                String species = petValue.getString("species");
                pet.setSpecies(species);
                double weight = petValue.getDouble("weight");
                pet.setWeight(weight);


                // book.setNumberOfPages(numberOfPages);
                // book.setPublicationDate(publicationDate);
                pets.add(pet);
            }



        } catch (Exception e) {
            System.out.println("ERROR\n" + e);
        }

        // Done
        return pets;

    }
}
