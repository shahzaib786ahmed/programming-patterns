package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Client extends User{
    private String userName;
    private String password;
    private int loyaltyPoints;

    public Client(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, String userName, String password, int loyaltyPoints) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age);
        this.userName = userName;
        this.password = password;
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isLogin(String userName, String password) {
        if (userName == null || password == null) {
            return false;
        }
        return this.userName.equals(userName) && this.password.equals(password);
    }
    //i think we should remove the request process we should just imagine that the client will call the agency and a employee will answer and book,cancel,etc for them directly
//    public void requestFlightBooking(String fName,String lName, String phoneNumber, String emailAddress, int age, String passportNum) {
//        //TODO:ADD FLIGHT BOOKING LOGIC
//        System.out.println("Flight booking requested for: " + fName + " " + lName);
//
//    }
//    public void requestHotelBooking(String fName,String lName, String phoneNumber, String emailAddress, int age, String passportNum) {
//      //TODO:ADD HOTEL BOOKING LOGIC
//        System.out.println("Hotel booking requested for: " + fName + " " + lName);
//
//    }
    public void viewTicketDetails(List<Ticket> tickets, int ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId() == ticketId) {
                System.out.println("Ticket Details:");
                System.out.println("Ticket ID: " + ticket.getTicketId());
                System.out.println("Ticket Status: " + ticket.getTicketStatus());
                // Add more ticket details if necessary
                return; // Exit the loop after finding the ticket
            }
        }
        System.out.println("Ticket with ID " + ticketId + " not found.");
    }
    public int pointsAccumulate(int loyaltyPoints) {
        return this.loyaltyPoints + loyaltyPoints;
    }
    @Override
    public void displayDetails() {
        System.out.println("Client Details:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Client{" +
                "userName='" + userName + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                ", User Details=" + super.toString() +
                '}';
    }
}
