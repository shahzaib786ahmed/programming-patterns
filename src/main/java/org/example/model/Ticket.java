package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

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
    private String paymentType;

    private static int idCounter = 0;

    public Ticket(Flight flight, Client client, String seatNumber, String departureDate, String returnDate, String paymentType) {
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
        this.paymentType = paymentType;
    }

    public Ticket(Flight flight, Client client, String seatNumber, String departureDate,String paymentType) {
        this.ticketId = ++idCounter;
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

    public Ticket(Flight flight, String seatNumber, String departureDate, String returnDate,String paymentType) {
        this.ticketId = ++idCounter;
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

    public Ticket(Flight flight, String seatNumber, String departureDate,String paymentType) {
        this.ticketId = ++idCounter;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketId == ticket.ticketId && Objects.equals(flight, ticket.flight) && Objects.equals(client, ticket.client) && Objects.equals(seatNumber, ticket.seatNumber) && ticketStatus == ticket.ticketStatus && Objects.equals(bookedBy, ticket.bookedBy) && Objects.equals(operationHistory, ticket.operationHistory) && Objects.equals(departureDate, ticket.departureDate) && Objects.equals(assignedTo, ticket.assignedTo) && Objects.equals(returnDate, ticket.returnDate) && Objects.equals(paymentType, ticket.paymentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, flight, client, seatNumber, ticketStatus, bookedBy, operationHistory, departureDate, assignedTo, returnDate, paymentType);
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
