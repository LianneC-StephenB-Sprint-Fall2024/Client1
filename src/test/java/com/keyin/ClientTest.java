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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpMethod;


class ClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client("http://localhost:8080/api", restTemplate); // Inject mock RestTemplate
    }

    // Testing Aircraft endpoints
    @Test
    void testGetAllAircraft() {
        Aircraft[] mockAircrafts = { new Aircraft(1, "TypeA", "AirlineA", 150), new Aircraft(2, "TypeB", "AirlineB", 200) };
        when(restTemplate.getForEntity(eq("http://localhost:8080/api/aircraft"), eq(Aircraft[].class)))
                .thenReturn(new ResponseEntity<>(mockAircrafts, HttpStatus.OK));
        List<Aircraft> aircraftList = client.getAllAircraft();
        assertEquals(2, aircraftList.size());
        assertEquals("TypeA", aircraftList.get(0).getType());
    }

    @Test
    void testGetAircraftById() {
        Aircraft mockAircraft = new Aircraft(1, "TypeA", "AirlineA", 150);
        when(restTemplate.getForObject("http://localhost:8080/api/aircraft/1", Aircraft.class))
                .thenReturn(mockAircraft);
        Aircraft aircraft = client.getAircraftById(1);
        assertEquals("TypeA", aircraft.getType());
    }

    @Test
    void testCreateAircraft() {
        Aircraft newAircraft = new Aircraft(null, "TypeA", "AirlineA", 150);
        Aircraft mockResponse = new Aircraft(1, "TypeA", "AirlineA", 150);
        when(restTemplate.postForObject(eq("http://localhost:8080/api/aircraft"), any(HttpEntity.class), eq(Aircraft.class)))
                .thenReturn(mockResponse);
        Aircraft createdAircraft = client.createAircraft(newAircraft);
        assertEquals(1, createdAircraft.getId());
    }

    @Test
    void testUpdateAircraft() {
        Aircraft updatedAircraft = new Aircraft(1, "UpdatedType", "UpdatedAirline", 180);
        when(restTemplate.exchange(eq("http://localhost:8080/api/aircraft/1"), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Aircraft.class)))
                .thenReturn(new ResponseEntity<>(updatedAircraft, HttpStatus.OK));
        Aircraft result = client.updateAircraft(1, updatedAircraft);
        assertEquals("UpdatedType", result.getType());
    }

    @Test
    void testDeleteAircraft() {
        restTemplate.delete("http://localhost:8080/api/aircraft/1");
        // No assertion needed for delete; we only verify that it does not throw an exception.
    }

    // Similar tests for Passenger CRUD endpoints
    @Test
    void testGetAllPassengers() {
        Passenger[] mockPassengers = { new Passenger(1, "John", "Doe", "123456789"), new Passenger(2, "Jane", "Smith", "987654321") };
        when(restTemplate.getForEntity(eq("http://localhost:8080/api/passengers"), eq(Passenger[].class)))
                .thenReturn(new ResponseEntity<>(mockPassengers, HttpStatus.OK));
        List<Passenger> passengers = client.getAllPassengers();
        assertEquals(2, passengers.size());
        assertEquals("John", passengers.get(0).getFirstName());
    }

    @Test
    void testCreatePassenger() {
        Passenger newPassenger = new Passenger(null, "John", "Doe", "123456789");
        Passenger mockResponse = new Passenger(1, "John", "Doe", "123456789");
        when(restTemplate.postForObject(eq("http://localhost:8080/api/passengers"), any(HttpEntity.class), eq(Passenger.class)))
                .thenReturn(mockResponse);
        Passenger createdPassenger = client.createPassenger(newPassenger);
        assertEquals(1, createdPassenger.getId());
    }

    // Add similar tests for City endpoints
    @Test
    void testGetAllCities() {
        City[] mockCities = { new City(1, "New York", "NY", 8000000), new City(2, "Los Angeles", "CA", 4000000) };
        when(restTemplate.getForEntity(eq("http://localhost:8080/api/cities"), eq(City[].class)))
                .thenReturn(new ResponseEntity<>(mockCities, HttpStatus.OK));
        List<City> cities = client.getAllCities();
        assertEquals(2, cities.size());
        assertEquals("New York", cities.get(0).getName());
    }

    // Add similar tests for Airport endpoints
    @Test
    void testGetAllAirports() {
        Airport[] mockAirports = { new Airport(1, "JFK", "John F. Kennedy International"), new Airport(2, "LAX", "Los Angeles International") };
        when(restTemplate.getForEntity(eq("http://localhost:8080/api/airports"), eq(Airport[].class)))
                .thenReturn(new ResponseEntity<>(mockAirports, HttpStatus.OK));
        List<Airport> airports = client.getAllAirports();
        assertEquals(2, airports.size());
        assertEquals("JFK", airports.get(0).getCode());
    }
}
