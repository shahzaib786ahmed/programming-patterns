package org.example.model;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    private Flight flight;
    private Client client;
    private String seatNumber;
    private Status ticketStatus;
    private Employee bookedBy;
    private MyQueue<Operation> operationHistory;
    private String departureDate;
    private String arrivalDate;

    private static int idCounter = 0;

    public Ticket(Flight flight, Client client, String seatNumber, Status ticketStatus, Employee bookedBy, MyQueue<Operation> operationHistory, String departureDate, String arrivalDate) {
        this.ticketId = ++idCounter;
        this.flight = flight;
        this.client = client;
        this.seatNumber = seatNumber;
        this.ticketStatus = ticketStatus;
        this.bookedBy = bookedBy;
        this.operationHistory = new MyQueue<>();
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Status getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Status ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Employee getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(Employee bookedBy) {
        this.bookedBy = bookedBy;
    }

    public MyQueue<Operation> getOperationHistory() {
        return operationHistory;
    }

    public void setOperationHistory(MyQueue<Operation> operationHistory) {
        this.operationHistory = operationHistory;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void displayDetails() {
        System.out.println("Ticket Details:");
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Flight: " + (flight.getFlightNumber()));
        System.out.println("Client: " + (client));
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Ticket Status: " + (ticketStatus));
        System.out.println("Booked By: " + (bookedBy.getUserId() + " | " + bookedBy.getlName() + ", " + bookedBy.getfName()));
        System.out.println("Operation History: " + (operationHistory != null ? operationHistory.toString() : "Not available"));
        System.out.println("Departure Date: " + departureDate);
        System.out.println("Arrival Date: " + arrivalDate);
    }

    public static class Operation {
        LocalDateTime operationTime;
        User operatedBy;
        String description;

        public Operation(User operatedBy, String description) {
            this.operatedBy = operatedBy;
            this.description = description;
        }

        @Override
        public String toString() {
            return operationTime + ": " + operatedBy + ": " + description;
        }
    }
}