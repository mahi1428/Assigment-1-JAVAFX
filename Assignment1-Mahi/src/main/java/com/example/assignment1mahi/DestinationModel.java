// The model class simply contains the base properties , methods and constructors that we will use in our controller class.
package com.example.assignment1mahi;
public class DestinationModel {
    private int id;
    private String destination;
    private int popularity;

    // Constructor
    public DestinationModel(int id, String destination, int popularity) {
        this.id = id;
        this.destination = destination;
        this.popularity = popularity;
    }

    // Getter and Setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for Province
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    // Getter and Setter for Unemployment Rate
    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}