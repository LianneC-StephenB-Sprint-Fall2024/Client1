package com.keyin;

import model.Aircraft;
import model.Passenger;
import model.City;
import model.Airport;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class Client {
    private final String baseUrl;
    private final RestTemplate restTemplate;

    public Client(String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
    }

    // Aircraft CRUD Operations
    public List<Aircraft> getAllAircraft() {
        ResponseEntity<Aircraft[]> response = restTemplate.getForEntity(baseUrl + "/aircraft", Aircraft[].class);
        return Arrays.asList(response.getBody());
    }

    public Aircraft getAircraftById(int id) {
        return restTemplate.getForObject(baseUrl + "/aircraft/" + id, Aircraft.class);
    }

    public Aircraft createAircraft(Aircraft aircraft) {
        HttpEntity<Aircraft> request = new HttpEntity<>(aircraft);
        return restTemplate.postForObject(baseUrl + "/aircraft", request, Aircraft.class);
    }

    public Aircraft updateAircraft(int id, Aircraft aircraft) {
        HttpEntity<Aircraft> request = new HttpEntity<>(aircraft);
        ResponseEntity<Aircraft> response = restTemplate.exchange(baseUrl + "/aircraft/" + id, HttpMethod.PUT, request, Aircraft.class);
        return response.getBody();
    }

    public void deleteAircraft(int id) {
        restTemplate.delete(baseUrl + "/aircraft/" + id);
    }

    // Passenger CRUD Operations
    public List<Passenger> getAllPassengers() {
        ResponseEntity<Passenger[]> response = restTemplate.getForEntity(baseUrl + "/passengers", Passenger[].class);
        return Arrays.asList(response.getBody());
    }

    public Passenger getPassengerById(int id) {
        return restTemplate.getForObject(baseUrl + "/passengers/" + id, Passenger.class);
    }

    public Passenger createPassenger(Passenger passenger) {
        HttpEntity<Passenger> request = new HttpEntity<>(passenger);
        return restTemplate.postForObject(baseUrl + "/passengers", request, Passenger.class);
    }

    public Passenger updatePassenger(int id, Passenger passenger) {
        HttpEntity<Passenger> request = new HttpEntity<>(passenger);
        ResponseEntity<Passenger> response = restTemplate.exchange(baseUrl + "/passengers/" + id, HttpMethod.PUT, request, Passenger.class);
        return response.getBody();
    }

    public void deletePassenger(int id) {
        restTemplate.delete(baseUrl + "/passengers/" + id);
    }

    // City CRUD Operations
    public List<City> getAllCities() {
        ResponseEntity<City[]> response = restTemplate.getForEntity(baseUrl + "/cities", City[].class);
        return Arrays.asList(response.getBody());
    }

    public City getCityById(int id) {
        return restTemplate.getForObject(baseUrl + "/cities/" + id, City.class);
    }

    public City createCity(City city) {
        HttpEntity<City> request = new HttpEntity<>(city);
        return restTemplate.postForObject(baseUrl + "/cities", request, City.class);
    }

    public City updateCity(int id, City city) {
        HttpEntity<City> request = new HttpEntity<>(city);
        ResponseEntity<City> response = restTemplate.exchange(baseUrl + "/cities/" + id, HttpMethod.PUT, request, City.class);
        return response.getBody();
    }

    public void deleteCity(int id) {
        restTemplate.delete(baseUrl + "/cities/" + id);
    }

    // Airport CRUD Operations
    public List<Airport> getAllAirports() {
        ResponseEntity<Airport[]> response = restTemplate.getForEntity(baseUrl + "/airports", Airport[].class);
        return Arrays.asList(response.getBody());
    }

    public Airport getAirportById(int id) {
        return restTemplate.getForObject(baseUrl + "/airports/" + id, Airport.class);
    }

    public Airport createAirport(Airport airport) {
        HttpEntity<Airport> request = new HttpEntity<>(airport);
        return restTemplate.postForObject(baseUrl + "/airports", request, Airport.class);
    }

    public Airport updateAirport(int id, Airport airport) {
        HttpEntity<Airport> request = new HttpEntity<>(airport);
        ResponseEntity<Airport> response = restTemplate.exchange(baseUrl + "/airports/" + id, HttpMethod.PUT, request, Airport.class);
        return response.getBody();
    }

    public void deleteAirport(int id) {
        restTemplate.delete(baseUrl + "/airports/" + id);
    }

    public static void main(String[] args) {
        Client client = new Client("http://localhost:8080/api");

        // Testing some endpoints
        System.out.println("All Aircrafts:");
        client.getAllAircraft().forEach(System.out::println);

        System.out.println("\nAll Passengers:");
        client.getAllPassengers().forEach(System.out::println);

        System.out.println("\nAll Cities:");
        client.getAllCities().forEach(System.out::println);

        System.out.println("\nAll Airports:");
        client.getAllAirports().forEach(System.out::println);

        // Add additional testing for POST, PUT, DELETE if needed
    }
}
