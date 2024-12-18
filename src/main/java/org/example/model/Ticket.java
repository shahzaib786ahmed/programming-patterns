package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Ticket {
    private int ticketId;
    private Flight flight;
    private Client client;
    private String seatNumber;
    private Status ticketStatus;
    private Employee bookedBy;
    private MyQueue<Operation> operationHistory;
    private String departureDate;
    private Employee assignedTo;
    private String returnDate;

    private static int idCounter = 0;

    public Ticket(Flight flight, Employee employee, Client client, String seatNumber, String departureDate, String returnDate) {
        this.ticketId = ++idCounter;
        this.flight = flight;
        this.client = client;
        this.seatNumber = seatNumber;
        this.assignedTo = null;
        this.ticketStatus = Status.CREATED;
        this.operationHistory = new MyQueue<>();
        this.operationHistory.add(new Operation(bookedBy, "Ticket Created"));
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public Ticket(Flight flight, Employee employee, Client client, String seatNumber, String departureDate) {
        this.ticketId = ++idCounter;
        this.flight = flight;
        this.client = client;
        this.seatNumber = seatNumber;
        this.assignedTo = null;
        this.ticketStatus = Status.CREATED;
        this.operationHistory = new MyQueue<>();
        this.operationHistory.add(new Operation(bookedBy, "Ticket Created"));
        this.departureDate = departureDate;
    }

    public Ticket(Flight flight, Employee employee, String seatNumber, String departureDate, String returnDate) {
        this.ticketId = ++idCounter;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.assignedTo = null;
        this.ticketStatus = Status.CREATED;
        this.operationHistory = new MyQueue<>();
        this.operationHistory.add(new Operation(bookedBy, "Ticket Created"));
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public Ticket(Flight flight, Employee employee, String seatNumber, String departureDate) {
        this.ticketId = ++idCounter;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.assignedTo = null;
        this.ticketStatus = Status.CREATED;
        this.operationHistory = new MyQueue<>();
        this.operationHistory.add(new Operation(bookedBy, "Ticket Created"));
        this.departureDate = departureDate;
    }


    public void displayDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("Ticket Details:");
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Flight: " + (flight.getFlightNumber()));
        if (client != null) {
            System.out.println("Client: " + (client));
            System.out.println("Loyalty Points: " + client.getLoyaltyPoints());
        } else {
            System.out.println("Employee Ticket");
        }
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Ticket Status: " + (ticketStatus));
//        System.out.println("Booked By: " +  + " | " + bookedBy.getLName() + ", " + bookedBy.getFName());
        System.out.println("Departure Date: " + departureDate);
        if (returnDate == null) {
            System.out.println("Price: " + (flight.getPrice() / 2));
        } else {
            System.out.println("Arrival Date: " + returnDate);
            System.out.println("Price: " + (flight.getPrice()));
        }
        System.out.println("--------------------------------------------------");
    }

    public static class Operation {
        LocalDateTime operationTime;
        User operatedBy;
        String description;

        public Operation(User operatedBy, String description) {
            this.operatedBy = operatedBy;
            this.description = description;
            this.operationTime = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return operationTime + ": " + operatedBy + ": " + description;
        }
    }
}