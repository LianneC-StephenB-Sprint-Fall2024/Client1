package model;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private List<Aircraft> aircraftList = new ArrayList<>();
    private Airport airport;

    // No-argument constructor
    public Passenger() {}

    // Constructor with parameters
    public Passenger(Integer id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Method to return the full name
    public String getName() {
        return firstName + " " + lastName;
    }

    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }

    // New getter for airport
    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Integer getAirportId() {
        return airport != null ? airport.getId() : null; // Returns null if airport is not set
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}
