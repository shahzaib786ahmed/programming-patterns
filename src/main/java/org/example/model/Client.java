package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter

public class Client extends User {
    private int loyaltyPoints;
    private List<Double> paymentHistory = new ArrayList<>();

    public Client(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, String username, String password, int loyaltyPoints) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age, username , password);
        this.loyaltyPoints = loyaltyPoints;
    }

    /**
     * Sets the loyalty points for the client
     *
     * @param loyaltyPoints of the client
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints += loyaltyPoints;
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
