package org.example.controller;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.model.*;

import java.util.List;
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)

public class Employee extends User {
    private List<Ticket> requestedBookings;
    @Setter
    private double discountRate;

    public Employee(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age);
        this.discountRate = discountRate;
    }

    /**
     * Displays all bought tickets
     */
    public static void viewAllBoughtTickets(){
        if (!TicketSystem.getBoughtTickets().isEmpty()) {
            for (Ticket ticket : TicketSystem.getBoughtTickets()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Ticket: ");
                ticket.displayDetails();
                System.out.println("--------------------------------------------------");
            }
        }
    }

    /**
     * Displays all cancelled tickets
     */
    public static void viewAllCanceledTickets(){
        if (!TicketSystem.getCancelledTickets().isEmpty()) {
            for (Ticket ticket : TicketSystem.getCancelledTickets()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Ticket: ");
                ticket.displayDetails();
                System.out.println("--------------------------------------------------");
            }
        }
    }

    /**
     * Makes the payment for the flight ticket for the passenger
     * @param ticket of the passenger
     * @param paymentType to make the payment for the ticket
     * @param recipientEmail of the passenger to send email of confirmation
     * @param creditCardNumber of the passenger to be used if paid by credit card
     */
    public void makePayment(Ticket ticket, String paymentType, String recipientEmail, String creditCardNumber) {
        double flightPrice = ticket.getFlight().getPrice();
        double serviceFee = 100.00;
        double totalCost = flightPrice + serviceFee;

        if ("CREDIT".equalsIgnoreCase(paymentType)) {
            if (!creditCardNumber.matches("\\d{16}")) {
                System.out.println("Invalid credit card number format. Payment failed.");
                return;
            }
            System.out.println("Payment of $" + totalCost + " made successfully via credit card.");
        } else if ("LOYALTY".equalsIgnoreCase(paymentType)) {
            Client client = ticket.getClient();
            int loyaltyPoints = client.getLoyaltyPoints();
            if (loyaltyPoints < totalCost) {
                System.out.println("Insufficient loyalty points. Payment failed.");
                return;
            }
            client.setLoyaltyPoints(loyaltyPoints - (int) totalCost);
            System.out.println("Payment of $" + totalCost + " made successfully with loyalty points.");
        } else {
            System.out.println("Invalid payment type.");
            return;
        }

        sendConfirmationEmail(recipientEmail, "Payment Confirmation", "Your payment of $" + totalCost + " was successful for ticket " + ticket.getTicketId());
    }

    /**
     * Sends an email confirmation containing the payment details
     * @param recipientEmail of the passenger to send email of confirmation
     * @param subject line for payment confirmation
     * @param messageBody where the confirmation of the payment for the ticket will be written, with the total price paid
     */
    public void sendConfirmationEmail(String recipientEmail, String subject, String messageBody) {
        System.out.println("Sending email to: " + recipientEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + messageBody);
        System.out.println("Email sent successfully.");
    }

    /**
     * Purchases a two-way (round-trip) flight ticket for the client
     * @param flight to be booked for the passenger
     * @param employee that is booking the ticket
     * @param client that is purchasing the ticket
     * @param seatNumber of the passenger where they will be assigned
     * @param departureDate of the flight selected
     * @param returnDate of the flight selected
     * @param paymentType to make the payment for the ticket
     * @param recipientEmail of the passenger to send email of confirmation
     * @param creditCardNumber of the passenger to be used if paid by credit card
     * @return a new ticket for the passenger
     */
    public static Ticket purchaseFlightTicket(Flight flight, Employee employee, Client client, String seatNumber, String departureDate, String returnDate, String paymentType, String recipientEmail, String creditCardNumber) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, client, seatNumber, departureDate, returnDate,paymentType);
        employee.makePayment(ticket,paymentType,recipientEmail,creditCardNumber);
        TicketSystem.getBoughtTickets().add(ticket);
        ticket.setTicketStatus(Status.PURCHASED);
        client.setLoyaltyPoints((int) flight.getPrice() / 4);

        System.out.println("Ticket has been purchased for client: " + client.getLName() + ", " + client.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    /**
     * Purchases a two-way (round-trip) flight ticket for the employee
     * @param flight to be booked for the passenger
     * @param employee that is booking the ticket
     * @param seatNumber of the passenger where they will be assigned
     * @param departureDate of the flight selected
     * @param returnDate of the flight selected
     * @param paymentType to make the payment for the ticket
     * @param recipientEmail of the passenger to send email of confirmation
     * @param creditCardNumber of the passenger to be used to make the payment
     * @return a new ticket for the passenger
     */
    public static Ticket purchaseEmployeeFlightTicket(Flight flight, Employee employee, String seatNumber, String departureDate, String returnDate, String paymentType, String recipientEmail, String creditCardNumber) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, seatNumber, departureDate, returnDate, paymentType);
        flight.setPrice(flight.getPrice() - (flight.getPrice() * (employee.discountRate / 100)));
        employee.makePayment(ticket,paymentType,recipientEmail,creditCardNumber);
        TicketSystem.getBoughtTickets().add(ticket);
        ticket.setTicketStatus(Status.PURCHASED);

        System.out.println("Ticket has been purchased for employee: " + employee.getLName() + ", " + employee.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    /**
     * Purchases a one-way flight ticket for the client
     * @param flight to be booked for the passenger
     * @param employee that is booking the ticket
     * @param client that is purchasing the ticket
     * @param seatNumber of the passenger where they will be assigned
     * @param departureDate of the flight selected
     * @param paymentType to make the payment for the ticket
     * @param recipientEmail of the passenger to send email of confirmation
     * @param creditCardNumber of the passenger to be used if paid by credit card
     * @return a new ticket for the passenger
     */
    public static Ticket purchaseOneWayFlightTicket(Flight flight, Employee employee, Client client, String seatNumber, String departureDate, String paymentType, String recipientEmail, String creditCardNumber) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, client, seatNumber, departureDate,paymentType);
        employee.makePayment(ticket,paymentType,recipientEmail,creditCardNumber);
        TicketSystem.getBoughtTickets().add(ticket);
        ticket.setTicketStatus(Status.PURCHASED);
        client.setLoyaltyPoints((int) flight.getPrice() / 4);

        System.out.println("Ticket has been purchased for client: " + client.getLName() + ", " + client.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    /**
     * Purchases a one-way flight ticket for the employee
     * @param flight to be booked for the passenger
     * @param employee that is booking the ticket
     * @param seatNumber of the passenger where they will be assigned
     * @param departureDate of the flight selected
     * @param paymentType to make the payment for the ticket
     * @param recipientEmail of the passenger to send email of confirmation
     * @param creditCardNumber of the passenger to be used to make the payment
     * @return a new ticket for the passenger
     */
    public static Ticket purchaseEmployeeOneWayFlightTicket(Flight flight, Employee employee, String seatNumber, String departureDate, String paymentType, String recipientEmail, String creditCardNumber) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, seatNumber, departureDate,paymentType);
        flight.setPrice(flight.getPrice() - (flight.getPrice() * (employee.discountRate / 100)));
        ticket.setTicketStatus(Status.PURCHASED);
        TicketSystem.getBoughtTickets().add(ticket);
        employee.makePayment(ticket,paymentType,recipientEmail,creditCardNumber);

        System.out.println("Ticket has been purchased for employee: " + employee.getLName() + ", " + employee.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    /**
     * Refunds a ticket to the passenger
     * @param ticket of the passenger that wants/needs a refund
     */
    public void refund(Ticket ticket) {
        Client client = ticket.getClient();
        String paymentType = ticket.getPaymentType();

        if ("CREDIT".equalsIgnoreCase(paymentType)) {
            System.out.println("Refund processed to credit card for ticket " + ticket.getTicketId());
        } else if ("LOYALTY".equalsIgnoreCase(paymentType)) {
            client.setLoyaltyPoints(client.getLoyaltyPoints() + (int) ticket.getFlight().getPrice());
            System.out.println("Refund processed using loyalty points for ticket " + ticket.getTicketId());
        }
        sendConfirmationEmail(client.getEmailAddress(), "Cancellation Confirmation", "Your ticket " + ticket.getTicketId() + " has been cancelled and a refund has been processed.");
    }

    /**
     * Cancels a flight ticket for the passenger
     * @param ticket of the passenger that wants/needs to cancel their flight
     */
    public static void cancelFlightTicket(Employee employee, Ticket ticket) {
        ticket.setTicketStatus(Status.CANCELLED);
        TicketSystem.getBoughtTickets().remove(ticket);
        TicketSystem.getCancelledTickets().add(ticket);
        ticket.getFlight().setFlightSeatNumber(ticket.getFlight().getFlightSeatNumber() + 1);
        employee.refund(ticket);
        System.out.println("Ticket: " + ticket.getTicketId() + " has been cancelled.");
    }

    /**
     * Books a hotel room for a client
     * @param employee that is booking the room
     * @param client that is purchasing the ticket
     * @param room to be booked
     * @param numOfNights number of nights that the passenger is staying
     */
    public static void bookRoomForClient(Employee employee, Client client, Room room, int numOfNights) {
        if (!HotelSystem.bookRoom(client, room, numOfNights)) {
            System.out.println("Unable to book the room for the client.");
        }
        HotelSystem.getClients().add(client);
        HotelSystem.getAvailableRooms().remove(room);
        HotelSystem.getBookedRooms().add(room);
       //TODO: PAYMENT FOR THE OF THE HOTEL ROOM BOOKING
    }

    /**
     * Cancels a hotel room for the customer
     * @param room to be cancelled
     */
    public void cancelRoomBooking(Room room) {
        if (!HotelSystem.cancelBooking(room)) {
            System.out.println("Unable to cancel the room booking.");
        }
        HotelSystem.getClients().remove(room.getClient());
        HotelSystem.getBookedRooms().remove(room);
        HotelSystem.getAvailableRooms().add(room);
        //TODO: REFUND FOR THE OF THE HOTEL ROOM BOOKING
    }

    /**
     * Display the details of the employee
     */
    @Override
    public void displayDetails() {
        System.out.println("Employee: " + super.toString());
    }
}
