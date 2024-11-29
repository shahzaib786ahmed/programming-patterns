package org.example.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Manager extends Employee {
    public Manager(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age, discountRate);
    }

    /**
     * Display the details of the manager
     */
    @Override
    public void displayDetails() {
        System.out.println("Manager: " + super.toString());
    }
}
