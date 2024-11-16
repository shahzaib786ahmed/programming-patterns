package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class Client extends User{
    private String userName;
    private String password;
    private int loyaltyPoints;
    private List<Double> paymentHistory = new ArrayList<>();

    public Client(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, String userName, String password) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age);
        this.userName = userName;
        this.password = password;
    }

    /**
     * Sets the loyalty points for the client
     * @param loyaltyPoints of the client
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints += loyaltyPoints;
    }

    /**
     * Checks to see if the correct user is logging in
     * @param userName of the client
     * @param password of the client
     * @return the boolean and checks if the username and password is correct, if not, return false
     */
    public boolean isLogin(String userName, String password) {
        if (userName == null || password == null) {
            return false;
        }
        return this.userName.equals(userName) && this.password.equals(password);
    }

    /**
     * Checks a ticket from the list of bought ticket(s) of a client
     * @param tickets list of the client
     * @param ticketId of the ticket to be searched for in the list of ticket(s)
     */
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

    /**
     * Display the details of the client
     */
    @Override
    public void displayDetails() {
        System.out.println("Client Details:");
        System.out.println(super.toString());
    }
}
