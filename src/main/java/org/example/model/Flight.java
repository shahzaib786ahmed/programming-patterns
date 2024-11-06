package org.example.model;

public class Flight extends Services{
    private String flightNumber;
    private String airline;
    private double price;
    private int flightSeatNumber;
    private String departureLocation;
    private String arrivalLocation;
    private String departureTime;
    private String arrivalTime;

    public Flight(String serviceName, double price, String description, String flightNumber, String airline, double price1, int flightSeatNumber, String departureLocation, String arrivalLocation, String departureTime, String arrivalTime) {
        super(serviceName, price, description);
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.price = price1;
        this.flightSeatNumber = flightSeatNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public void displayDetails() {

    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    public int getFlightSeatNumber() {
        return flightSeatNumber;
    }

    public void setFlightSeatNumber(int flightSeatNumber) {
        this.flightSeatNumber = flightSeatNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
