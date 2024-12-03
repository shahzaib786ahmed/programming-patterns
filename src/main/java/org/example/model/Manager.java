package org.example.model;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Manager extends Employee {
    public Manager(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, String username, String password, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age, username, password, discountRate);
    }

    /**
     * Display the details of the manager
     */
    @Override
    public void displayDetails() {
        System.out.println("Manager: " + super.toString());
    }
}
