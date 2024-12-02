package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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
    private String paymentType;

    private static int idCounter = 1;

    public Ticket(Flight flight, Client client, String seatNumber, String departureDate, String returnDate, String paymentType) {
        this.ticketId = idCounter++;
        this.flight = flight;
        this.client = client;
        this.seatNumber = seatNumber;
        this.assignedTo = null;
        this.ticketStatus = Status.CREATED;
        this.operationHistory = new MyQueue<>();
        this.operationHistory.add(new Operation(bookedBy, "Ticket Created"));
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.paymentType = paymentType;
    }

    public Ticket(Flight flight, Client client, String seatNumber, String departureDate, String paymentType) {
        this.ticketId = idCounter++;
        this.flight = flight;
        this.client = client;
        this.seatNumber = seatNumber;
        this.assignedTo = null;
        this.ticketStatus = Status.CREATED;
        this.operationHistory = new MyQueue<>();
        this.operationHistory.add(new Operation(bookedBy, "Ticket Created"));
        this.departureDate = departureDate;
        this.paymentType = paymentType;
    }

    public Ticket(Flight flight, String seatNumber, String departureDate, String returnDate, String paymentType) {
        this.ticketId = idCounter++;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.assignedTo = null;
        this.ticketStatus = Status.CREATED;
        this.operationHistory = new MyQueue<>();
        this.operationHistory.add(new Operation(bookedBy, "Ticket Created"));
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.paymentType = paymentType;
    }

    public Ticket(Flight flight, String seatNumber, String departureDate, String paymentType) {
        this.ticketId = idCounter++;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.assignedTo = null;
        this.ticketStatus = Status.CREATED;
        this.operationHistory = new MyQueue<>();
        this.operationHistory.add(new Operation(bookedBy, "Ticket Created"));
        this.departureDate = departureDate;
        this.paymentType = paymentType;
    }

    /**
     * Details of the flight ticket
     */
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
        System.out.println("Departure Date: " + departureDate);
        if (returnDate == null) {
            System.out.println("Price: " + (flight.getPrice() / 2));
        } else {
            System.out.println("Arrival Date: " + returnDate);
            System.out.println("Price: " + (flight.getPrice()));
        }
        System.out.println("Payment type: " + paymentType);
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

        /**
         * Displays the operation time of the ticket
         *
         * @return a string containing the info of the operation
         */
        @Override
        public String toString() {
            return operationTime + ": " + operatedBy + ": " + description;
        }
    }
}
