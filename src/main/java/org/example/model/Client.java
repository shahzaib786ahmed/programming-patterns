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
