package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Client extends User {
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
