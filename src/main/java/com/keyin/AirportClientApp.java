package com.keyin;

import model.Aircraft;
import model.Passenger;
import model.City;
import model.Airport;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
import java.util.List;

public class AirportClientApp {
    private final Client client;

    public AirportClientApp(String baseUrl, RestTemplate restTemplate) {
        this.client = new Client(baseUrl, restTemplate);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n----------------- International Airport Manager -----------------");
            System.out.println("\nChoose an option:");
            System.out.println("1. List all airports in cities");
            System.out.println("2. List all aircraft passengers have traveled on");
            System.out.println("3. List airports aircraft can take off from and land at");
            System.out.println("4. List airports passengers have used");
            System.out.println("5. Exit");
            System.out.println("\n-----------------------------------------------------------------");
            System.out.println("\nType your option:");
            int choice = scanner.nextInt();
           // scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> listAirportsInCities();
                case 2 -> listAircraftPassengers();
                case 3 -> listAircraftAirports();
                case 4 -> listAirportsPassengersUsed();
                case 5 -> {
                    System.out.println("Exiting.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    void listAirportsInCities() {
        System.out.println("\n---------------- List of the airports in cities  ----------------");
        List<City> cities = client.getAllCities();

        // Retrieve all airports once to avoid multiple calls
        List<Airport> airports = client.getAllAirports();

        cities.forEach(city -> {
            System.out.println("\nCity: " + city.getName());

            // Stream the airports list, filtering based on city ID
            airports.stream()
                    .filter(airport -> airport.getCity() != null && airport.getCity().getId().equals(city.getId()))
                    .forEach(airport -> System.out.println(" - Airport: " + airport.getName()));
        });
    }

    void listAircraftPassengers() {
        System.out.println("\n---------------- List of passengers in aircrafts ----------------");
        // Retrieve the list of all aircraft with their passengers
        List<Aircraft> aircraftList = client.getAllAircraft();

        // Iterate through each aircraft and display its passengers
        aircraftList.forEach(aircraft -> {
            System.out.println("\nAircraft: " + aircraft.getType() + " (Airline: " + aircraft.getAirlineName() + ")");

            // Directly print passengers from each aircraft
            aircraft.getPassengers().forEach(passenger ->
                    System.out.println(" - Passenger: " + passenger.getFirstName() + " " + passenger.getLastName())
            );
        });
    }


    void listAircraftAirports() {
        System.out.println("\n---------------- List the aircrafts in airports  ----------------");
        List<Aircraft> aircraftList = client.getAllAircraft();

        aircraftList.forEach(aircraft -> {
            System.out.println("\nAircraft: " + aircraft.getType() + " has access to the following airports:");
            aircraft.getAirports().forEach(airport ->
                    System.out.println(" - Airport: " + airport.getName())
            );
        });
    }

    void listAirportsPassengersUsed() {
        System.out.println("\n---------------- List the airport passenger used ----------------");
        List<Passenger> passengers = client.getAllPassengers();
        passengers.forEach(passenger -> {
            System.out.println("\nPassenger: " + passenger.getFirstName() + " " + passenger.getLastName());
            Integer airportId = passenger.getAirportId(); // Now you can call this method
            if (airportId != null) {
                Airport airport = client.getAirportById(airportId);
                if (airport != null) {
                    System.out.println(" - Used Airport: " + airport.getName() + " in " + airport.getLocation());
                } else {
                    System.out.println(" - Airport information not found.");
                }
            } else {
                System.out.println(" - No airport information available.");
            }
        });
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        AirportClientApp app = new AirportClientApp("http://localhost:8080/api", restTemplate);
        // Run the application
        app.run();
}
    }