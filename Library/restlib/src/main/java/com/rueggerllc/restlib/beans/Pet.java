package com.rueggerllc.restlib.beans;


import java.util.Date;

public class Pet {
    private String name;
    private String species;
    private double weight;

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Pet.name: " + name);
        buffer.append("\nPet.species: " + species);
        buffer.append("\nPet.weight: " + weight);
        return buffer.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
