package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)

public class Employee extends User {
    @Setter
    private double discountRate;

    public Employee(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, String username, String password, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age, username, password);
        this.discountRate = discountRate;
    }

    /**
     * Display the details of the employee
     */
    @Override
    public void displayDetails() {
        System.out.println("Employee: " + super.toString());
    }
}
