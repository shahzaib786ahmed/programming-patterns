package org.example.model;

import java.util.List;

public class Employee extends User{
    private List<Ticket> requestedBookings;
    private double discountRate;

    public Employee(String userId, String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, List<Ticket> requestedBookings, double discountRate) {
        super(userId, lName, fName, passportNum, phoneNumber, emailAddress, age);
        this.requestedBookings = requestedBookings;
        this.discountRate = discountRate;
    }

    public Ticket fetchNewTicket() {
        return null;
    }

    public void viewAllTickets(List<Ticket> tickets){

    }

    public Ticket purchaseTicket() {
        return null;
    }

    public void cancelTicket(Ticket ticket) {

    }

    public void changeTicketStatus(Ticket ticket, Ticket status) {

    }

    public void bookHotelRoom(Hotel hotel, Room room) {

    }

    public void changeRoomStatus(Room room, Room status) {

    }

    public void cancelHotelBooking(Room room) {

    }

    @Override
    public void displayDetails() {

    }
}
