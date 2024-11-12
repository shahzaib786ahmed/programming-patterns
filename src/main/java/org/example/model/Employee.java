package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
@Setter
@Getter
@EqualsAndHashCode
public class Employee extends User{
    private List<Ticket> requestedBookings;
    @Setter
    private double discountRate;

    public Employee(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age);
        this.discountRate = discountRate;
    }

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

    public static Ticket purchaseFlightTicket(Flight flight, Employee employee, Client client, String seatNumber, String departureDate, String arrivalDate) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, employee, client, seatNumber, departureDate, arrivalDate);
        ticket.setTicketStatus(Status.PURCHASED);
        TicketSystem.getBoughtTickets().add(ticket);
        client.setLoyaltyPoints((int) flight.getPrice() / 4);

        System.out.println("Ticket has been purchased for client: " + client.getLName() + ", " + client.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    public static Ticket purchaseEmployeeFlightTicket(Flight flight, Employee employee, String seatNumber, String departureDate, String arrivalDate) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, employee, seatNumber, departureDate, arrivalDate);
        flight.setPrice(flight.getPrice() - (flight.getPrice() * (employee.discountRate / 100)));
        ticket.setTicketStatus(Status.PURCHASED);
        TicketSystem.getBoughtTickets().add(ticket);

        System.out.println("Ticket has been purchased for employee: " + employee.getLName() + ", " + employee.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    public static Ticket purchaseOneWayFlightTicket(Flight flight, Employee employee, Client client, String seatNumber, String departureDate) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, employee, client, seatNumber, departureDate);
        ticket.setTicketStatus(Status.PURCHASED);
        TicketSystem.getBoughtTickets().add(ticket);
        client.setLoyaltyPoints((int) flight.getPrice() / 4);

        System.out.println("Ticket has been purchased for client: " + client.getLName() + ", " + client.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    public static Ticket purchaseEmployeeOneWayFlightTicket(Flight flight, Employee employee, String seatNumber, String departureDate) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        } else {
            flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
        }

        Ticket ticket = new Ticket(flight, employee, seatNumber, departureDate);
        flight.setPrice(flight.getPrice() - (flight.getPrice() * (employee.discountRate / 100)));
        ticket.setTicketStatus(Status.PURCHASED);
        TicketSystem.getBoughtTickets().add(ticket);

        System.out.println("Ticket has been purchased for employee: " + employee.getLName() + ", " + employee.getFName() +
                " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    public static void cancelFlightTicket(Ticket ticket) {
        ticket.setTicketStatus(Status.CANCELLED);
        TicketSystem.getBoughtTickets().remove(ticket);
        TicketSystem.getCancelledTickets().add(ticket);
        ticket.getFlight().setFlightSeatNumber(ticket.getFlight().getFlightSeatNumber() + 1);

        System.out.println("Ticket: " + ticket.getTicketId() + " has been cancelled.");
    }

    public static void bookRoomForClient(Employee employee, Client client, Room room, int numOfNights) {
        if (!HotelSystem.bookRoom(employee, client, room, numOfNights)) {
            System.out.println("Unable to book the room for the client.");
        }
        HotelSystem.getAvailableRooms().remove(room);
        HotelSystem.getBookedRooms().add(room);
    }

    public void cancelRoomBooking(HotelSystem hotelSystem, Room room) {
        if (!hotelSystem.cancelBooking(room)) {
            System.out.println("Unable to cancel the room booking.");
        }
        HotelSystem.getBookedRooms().remove(room);
        HotelSystem.getAvailableRooms().add(room);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(discountRate, employee.discountRate) == 0 && Objects.equals(requestedBookings, employee.requestedBookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestedBookings, discountRate);
    };

    @Override
    public void displayDetails() {
        System.out.println("Employee: " + super.toString());
    }
}
