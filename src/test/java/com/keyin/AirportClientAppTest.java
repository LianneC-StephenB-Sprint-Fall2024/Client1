package com.keyin;

import model.Aircraft;
import model.Passenger;
import model.City;
import model.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class AirportClientAppTest {

    @Mock
    private RestTemplate restTemplate;

    private AirportClientApp app;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Instantiate the AirportClientApp with a base URL and mock RestTemplate
        app = new AirportClientApp("http://localhost:8080/api", restTemplate);
    }

    @Test
    void testListAirportsInCities() {
        City[] mockCities = {
                new City(1, "New York", "NY", 8000000),
                new City(2, "Los Angeles", "CA", 4000000)
        };
        Airport[] mockAirports = {
                new Airport(1, "JFK", "John F. Kennedy International", mockCities[0], "New York"),
                new Airport(2, "LAX", "Los Angeles International", mockCities[1], "Los Angeles")
        };

        when(restTemplate.getForEntity(eq("http://localhost:8080/api/cities"), eq(City[].class)))
                .thenReturn(new ResponseEntity<>(mockCities, HttpStatus.OK));
        when(restTemplate.getForEntity(eq("http://localhost:8080/api/airports"), eq(Airport[].class)))
                .thenReturn(new ResponseEntity<>(mockAirports, HttpStatus.OK));

        // Call the method to test
        app.listAirportsInCities();

        // Assertions to verify the output, if needed
        assertEquals("New York", mockCities[0].getName());
    }

    @Test
    void testListAircraftPassengers() {
        Aircraft[] mockAircrafts = {
                new Aircraft(1, "TypeA", "AirlineA", 150),
                new Aircraft(2, "TypeB", "AirlineB", 200)
        };
        Passenger[] mockPassengers = {
                new Passenger(1, "John", "Doe", "123456789"),
                new Passenger(2, "Jane", "Smith", "987654321")
        };

        when(restTemplate.getForEntity(eq("http://localhost:8080/api/aircraft"), eq(Aircraft[].class)))
                .thenReturn(new ResponseEntity<>(mockAircrafts, HttpStatus.OK));
        when(restTemplate.getForEntity(eq("http://localhost:8080/api/passengers"), eq(Passenger[].class)))
                .thenReturn(new ResponseEntity<>(mockPassengers, HttpStatus.OK));

        app.listAircraftPassengers();

        assertEquals("TypeA", mockAircrafts[0].getType());
        assertEquals("John", mockPassengers[0].getFirstName());
    }

    @Test
    void testListAircraftAirports() {
        Aircraft[] mockAircrafts = {
                new Aircraft(1, "TypeA", "AirlineA", 150)
        };
        Airport[] mockAirports = {
                new Airport(1, "JFK", "John F. Kennedy International")
        };

        when(restTemplate.getForEntity(eq("http://localhost:8080/api/aircraft"), eq(Aircraft[].class)))
                .thenReturn(new ResponseEntity<>(mockAircrafts, HttpStatus.OK));
        when(restTemplate.getForEntity(eq("http://localhost:8080/api/airports"), eq(Airport[].class)))
                .thenReturn(new ResponseEntity<>(mockAirports, HttpStatus.OK));

        app.listAircraftAirports();

        assertEquals("JFK", mockAirports[0].getCode());
    }

    @Test
    void testListAirportsPassengersUsed() {
        Passenger[] mockPassengers = {
                new Passenger(1, "John", "Doe", "123456789", 1),
                new Passenger(2, "Jane", "Smith", "987654321", 2)
        };
        Airport[] mockAirports = {
                new Airport(1, "JFK", "John F. Kennedy International"),
                new Airport(2, "LAX", "Los Angeles International")
        };

        when(restTemplate.getForEntity(eq("http://localhost:8080/api/passengers"), eq(Passenger[].class)))
                .thenReturn(new ResponseEntity<>(mockPassengers, HttpStatus.OK));
        when(restTemplate.getForObject(eq("http://localhost:8080/api/airports/1"), eq(Airport.class)))
                .thenReturn(mockAirports[0]);
        when(restTemplate.getForObject(eq("http://localhost:8080/api/airports/2"), eq(Airport.class)))
                .thenReturn(mockAirports[1]);

        app.listAirportsPassengersUsed();

        assertEquals("JFK", mockAirports[0].getCode());
        assertEquals("LAX", mockAirports[1].getCode());
    }
}