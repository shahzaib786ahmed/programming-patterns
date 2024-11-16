package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Employee extends User{
    private List<Ticket> requestedBookings;
    @Setter
    private double discountRate;

    public Employee(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age);
        this.discountRate = discountRate;
    }

    /**
     *
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
     *
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
     *
     * @param ticket
     * @param paymentType
     * @param userId
     * @param email
     * @param creditCardNumber
     */
    public void makePayment(Ticket ticket, String paymentType, String userId, String email, String creditCardNumber) {
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

        sendConfirmationEmail(email, "Payment Confirmation", "Your payment of $" + totalCost + " was successful for ticket " + ticket.getTicketId());
    }

    /**
     *
     * @param recipientEmail
     * @param subject
     * @param messageBody
     */
    public void sendConfirmationEmail(String recipientEmail, String subject, String messageBody) {
        System.out.println("Sending email to: " + recipientEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + messageBody);
        System.out.println("Email sent successfully.");
    }

    /**
     *
     * @param flight
     * @param employee
     * @param client
     * @param seatNumber
     * @param departureDate
     * @param arrivalDate
     * @param paymentType
     * @param userId
     * @param email
     * @param creditCardNumber
     * @return
     */
    public static Ticket purchaseFlightTicket(Flight flight, Employee employee, Client client, String seatNumber, String departureDate, String arrivalDate, String paymentType, String userId, String email, String creditCardNumber) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, employee, client, seatNumber, departureDate, arrivalDate,paymentType);
        ticket.setTicketStatus(Status.PURCHASED);
        TicketSystem.getBoughtTickets().add(ticket);
        client.setLoyaltyPoints((int) flight.getPrice() / 4);
        employee.makePayment(ticket,paymentType,userId,email,creditCardNumber);

        System.out.println("Ticket has been purchased for client: " + client.getLName() + ", " + client.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    /**
     *
     * @param flight
     * @param employee
     * @param seatNumber
     * @param departureDate
     * @param arrivalDate
     * @param paymentType
     * @param userId
     * @param email
     * @param creditCardNumber
     * @return
     */
    public static Ticket purchaseEmployeeFlightTicket(Flight flight, Employee employee, String seatNumber, String departureDate, String arrivalDate, String paymentType, String userId, String email, String creditCardNumber) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, employee, seatNumber, departureDate, arrivalDate,paymentType);
        flight.setPrice(flight.getPrice() - (flight.getPrice() * (employee.discountRate / 100)));
        ticket.setTicketStatus(Status.PURCHASED);
        TicketSystem.getBoughtTickets().add(ticket);
        employee.makePayment(ticket,paymentType,userId,email,creditCardNumber);

        System.out.println("Ticket has been purchased for employee: " + employee.getLName() + ", " + employee.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    /**
     *
     * @param flight
     * @param employee
     * @param client
     * @param seatNumber
     * @param departureDate
     * @param paymentType
     * @param userId
     * @param email
     * @param creditCardNumber
     * @return
     */
    public static Ticket purchaseOneWayFlightTicket(Flight flight, Employee employee, Client client, String seatNumber, String departureDate, String paymentType, String userId, String email, String creditCardNumber) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, employee, client, seatNumber, departureDate,paymentType);
        ticket.setTicketStatus(Status.PURCHASED);
        TicketSystem.getBoughtTickets().add(ticket);
        client.setLoyaltyPoints((int) flight.getPrice() / 4);
        employee.makePayment(ticket,paymentType,userId,email,creditCardNumber);

        System.out.println("Ticket has been purchased for client: " + client.getLName() + ", " + client.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    /**
     *
     * @param flight
     * @param employee
     * @param seatNumber
     * @param departureDate
     * @param paymentType
     * @param userId
     * @param email
     * @param creditCardNumber
     * @return
     */
    public static Ticket purchaseEmployeeOneWayFlightTicket(Flight flight, Employee employee, String seatNumber, String departureDate,String paymentType,String userId, String email, String creditCardNumber) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, employee, seatNumber, departureDate,paymentType);
        flight.setPrice(flight.getPrice() - (flight.getPrice() * (employee.discountRate / 100)));
        ticket.setTicketStatus(Status.PURCHASED);
        TicketSystem.getBoughtTickets().add(ticket);
        employee.makePayment(ticket,paymentType,userId,email,creditCardNumber);

        System.out.println("Ticket has been purchased for employee: " + employee.getLName() + ", " + employee.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    /**
     *
     * @param ticket
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
     *
     * @param ticket
     */
    public static void cancelFlightTicket(Employee employee,Ticket ticket) {
        ticket.setTicketStatus(Status.CANCELLED);
        TicketSystem.getBoughtTickets().remove(ticket);
        TicketSystem.getCancelledTickets().add(ticket);
        ticket.getFlight().setFlightSeatNumber(ticket.getFlight().getFlightSeatNumber() + 1);
        employee.refund(ticket);
        System.out.println("Ticket: " + ticket.getTicketId() + " has been cancelled.");
    }

    /**
     *
     * @param employee
     * @param client
     * @param room
     * @param numOfNights
     */
    public static void bookRoomForClient(Employee employee, Client client, Room room, int numOfNights) {
        if (!HotelSystem.bookRoom(employee, client, room, numOfNights)) {
            System.out.println("Unable to book the room for the client.");
        }
        HotelSystem.getAvailableRooms().remove(room);
        HotelSystem.getBookedRooms().add(room);
       // TODO:PAYMENT
    }

    /**
     *
     * @param hotelSystem
     * @param room
     */
    public void cancelRoomBooking(HotelSystem hotelSystem, Room room) {
        if (!hotelSystem.cancelBooking(room)) {
            System.out.println("Unable to cancel the room booking.");
        }
        HotelSystem.getBookedRooms().remove(room);
        HotelSystem.getAvailableRooms().add(room);
       // TODO:REFUND
    }

    /**
     *
     */
    @Override
    public void displayDetails() {
        System.out.println("Employee: " + super.toString());
    }
}
