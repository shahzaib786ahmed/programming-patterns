package org.example.model;

import java.util.List;
import java.util.Objects;

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

    public Ticket purchaseTicket() {
        //TODO: ADD PURCHASING TICKET SYSTEM LOGIC
        return null;
    }

    public void cancelTicket(Ticket ticket) {
        //TODO: ADD CANCELLING TICKET SYSTEM LOGIC
    }

    public void changeTicketStatus(Ticket ticket, Status newStatus) {
        ticket.setTicketStatus(newStatus);
    }

    public void bookHotelRoom(Hotel hotel, Room room) {
        //TODO: ADD BOOKING HOTEL ROOM SYSTEM LOGIC
    }

    public void changeRoomStatus(Room room, Room.RoomStatus newStatus) {
        room.setRoomStatus(newStatus);
    }

    public void cancelHotelBooking(Room room) {
        //TODO: ADD CANCELLING HOTEL ROOM SYSTEM LOGIC
    }

    public List<Ticket> getRequestedBookings() {
        return requestedBookings;
    }

    public double getDiscountRate() {
        return discountRate;
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
