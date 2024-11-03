package com.keyin;

import model.Aircraft;
import model.Passenger;
import model.City;
import model.Airport;

import java.util.Scanner;
import java.util.List;

public class AirportClientApp {
    private final Client client;

    public AirportClientApp(String baseUrl) {
        this.client = new Client(baseUrl);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. List all airports in cities");
            System.out.println("2. List all aircraft passengers have traveled on");
            System.out.println("3. List airports aircraft can take off from and land at");
            System.out.println("4. List airports passengers have used");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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

    private void listAirportsInCities() {
        List<City> cities = client.getAllCities();
        cities.forEach(city -> {
            System.out.println("City: " + city.getName());

            List<Airport> airports = client.getAllAirports();
            airports.stream()
                    .filter(airport -> airport.getCity().getId().equals(city.getId()))
                    .forEach(airport -> System.out.println(" - Airport: " + airport.getName()));
        });
    }

    private void listAircraftPassengers() {
        // Retrieve the list of all aircraft with their passengers
        List<Aircraft> aircraftList = client.getAllAircraft();

        // Iterate through each aircraft and display its passengers
        aircraftList.forEach(aircraft -> {
            System.out.println("Aircraft: " + aircraft.getType() + " (Airline: " + aircraft.getAirlineName() + ")");

            // Directly print passengers from each aircraft
            aircraft.getPassengers().forEach(passenger ->
                    System.out.println(" - Passenger: " + passenger.getFirstName() + " " + passenger.getLastName())
            );
        });
    }


    private void listAircraftAirports() {
        List<Aircraft> aircraftList = client.getAllAircraft();

        aircraftList.forEach(aircraft -> {
            System.out.println("Aircraft: " + aircraft.getType() + " has access to the following airports:");
            aircraft.getAirports().forEach(airport ->
                    System.out.println(" - Airport: " + airport.getName())
            );
        });
    }

    private void listAirportsPassengersUsed() {
        List<Passenger> passengers = client.getAllPassengers();
        passengers.forEach(passenger -> {
            System.out.println("Passenger: " + passenger.getFirstName() + " " + passenger.getLastName());
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
        AirportClientApp app = new AirportClientApp("http://localhost:8080/api");
        app.run();
    }
}