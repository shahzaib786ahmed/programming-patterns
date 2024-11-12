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
    private double discountRate;

    public Employee(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age);
        this.discountRate = discountRate;
    }

    public static void viewAllTickets(){
        if (!TicketSystem.getBoughtTickets().isEmpty()) {
            for (Ticket ticket : TicketSystem.getBoughtTickets()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Ticket: ");
                ticket.displayDetails();
                System.out.println("--------------------------------------------------");
            }
        }
    }

    //REMOVED FETCHTICKET BECAUSE MANAGER FETCHES TICKET AND ADDS IT TO EMPLOYEE'S REQUESTED BOOKING LIST WHERE EMPLOYEE WILL RETRIEVE.

    public void viewRequestedBookings() {
        for (Ticket requestedBooking: requestedBookings) {
            System.out.println(requestedBooking);
        }
    }

    public static Ticket purchaseFlightTicket(Flight flight,Employee employe, Client client, String seatNumber, String departureDate, String arrivalDate) {
        //add if statement to check if eligible for employee discount.
        Ticket ticket = new Ticket(flight, client, seatNumber, departureDate, arrivalDate);
        ticket.setTicketStatus(Status.BOUGHT);
        TicketSystem.getBoughtTickets().add(ticket);

        System.out.println("Ticket has been purchased for client: " + client.getLName() + ", " + client.getFName() +
                " for flight: " + flight.getFlightNumber() + " from" + flight.getDepartureLocation() + " to" + flight.getArrivalLocation());
        System.out.println("Here's your ticket: ");
        ticket.displayDetails();
        return ticket;
    }

    public static void cancelFlightTicket(Ticket ticket) {
        ticket.setTicketStatus(Status.CANCELLED);
        TicketSystem.getBoughtTickets().remove(ticket);
        TicketSystem.getCancelledTickets().add(ticket);

        System.out.println("Ticket: " + ticket.getTicketId() + " has been cancelled.");
    }
// i dont think we need it?
//    public void changeTicketStatus(Ticket ticket, Status newStatus) {
//        ticket.setTicketStatus(newStatus);
//    }

    public void bookRoomForClient(Employee employee, Client client, Room room, int numOfNights) {
        if (!HotelSystem.bookRoom(this, client, room, numOfNights)) {
            System.out.println("Unable to book the room for the client.");
        }
    }

    public void cancelRoomBooking(HotelSystem hotelSystem, Room room) {
        if (!hotelSystem.cancelBooking(room)) {
            System.out.println("Unable to cancel the room booking.");
        }
    }


    public void setDiscountRate(double discountRate) {
        this.discountRate = 10;
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
