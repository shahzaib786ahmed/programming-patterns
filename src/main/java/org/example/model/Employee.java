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
    private static List<Ticket> requestedBookings;
    private double discountRate;

    public Employee(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age);
        this.discountRate = discountRate;
    }

    public Ticket fetchNewTicket() {
        //TODO: ADD TICKET FETCHING SYSTEM LOGIC
        return null;
        //CEST COMME CA QUE ON LA FAIT DANS ASS3 SO IT GIVES U AN IDEA
//        Ticket ticket = TicketSystem.getUnassignedTickets().poll();
//        TicketSystem.getProcessingTickets().add(ticket);
//        assignedTickets.add(ticket);
//        ticket.setStatus(Ticket.Status.ASSIGNED);
//        ticket.setAssignedTo(this);
//        ticket.getOperationHistory().add(new Ticket.Operation(this, "Assigned to " + this.getName()));
//        return ticket;
    }

    public void viewAllTickets(List<Ticket> tickets){
        if (!tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                System.out.println("--------------------------------------------------");
                System.out.println("Ticket: ");
                ticket.displayDetails();
                System.out.println("--------------------------------------------------");
            }
        }
    }
//MAYBE WE SHOULD NAME THEM FLIGHTBOOKING BECAUSE SO IT DOESNT CONFUSE US
//    public Ticket purchaseTicket() {
//        //TODO: ADD PURCHASING TICKET SYSTEM LOGIC
//        return null;
//    }
//
//    public void cancelTicket(Ticket ticket) {
//        //TODO: ADD CANCELLING TICKET SYSTEM LOGIC
//    }

    public void changeTicketStatus(Ticket ticket, Status newStatus) {
        ticket.setTicketStatus(newStatus);
    }

    public void bookRoomForClient(HotelSystem hotelSystem, Client client, Room room) {
        if (!hotelSystem.bookRoom(this, client, room)) {
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
