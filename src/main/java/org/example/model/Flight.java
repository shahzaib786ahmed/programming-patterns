package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Flight extends Services {
    private String flightNumber;
    private String airline;
    private double price;
    private int flightSeatNumber;
    private String departureLocation;
    private String arrivalLocation;
    private String departureTime;
    private String arrivalTime;

    public Flight(String serviceName, String description, String flightNumber, String airline, double price, int flightSeatNumber,String departureLocation, String arrivalLocation, String departureTime, String arrivalTime) {
        super(serviceName, description);
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.price = price;
        this.flightSeatNumber = flightSeatNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    /**
     *
     */
    @Override
    public void displayDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("Flight: " + getFlightNumber());
        System.out.println("Airline: " + getAirline());
        System.out.println("Departure Airport: " + getDepartureLocation());
        System.out.println("Arrival Airport: " + getArrivalLocation());
        System.out.println("Departure Time: " + getDepartureTime());
        System.out.println("Arrival Time: " + getArrivalTime());
        System.out.println("Flight Ticket Purchase Price: " + getPrice());
        System.out.println("--------------------------------------------------");
    }
}
