package model;

import java.util.ArrayList;
import java.util.List;

public class Aircraft {
    private Integer id;
    private String type;
    private String airlineName;
    private Integer numberOfPassengers;

    private List<Passenger> passengers = new ArrayList<>();

    private List<Airport> airports = new ArrayList<>();

    // No-argument constructor
    public Aircraft() {}

    // Constructor with parameters
    public Aircraft(Integer id, String type, String airlineName, Integer numberOfPassengers) {
        this.id = id;
        this.type = type;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports; }

    public List<Passenger> getPassengers() {
        return passengers; }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers; }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", numberOfPassengers=" + numberOfPassengers +
                '}';
    }
}
