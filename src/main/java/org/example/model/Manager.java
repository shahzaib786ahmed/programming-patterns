package org.example.model;

import java.util.List;

public class Manager extends Employee{
    private int numberOfEmployeesManaged;

    public Manager(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, List<Ticket> requestedBookings, double discountRate, int numberOfEmployeesManaged) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age, requestedBookings, discountRate);
        this.numberOfEmployeesManaged = numberOfEmployeesManaged;
    }

    public Employee assignTo(Ticket ticket, Employee employee) {
        return null;
    }

    public void addEmployee(String lName, String fName, String phoneNumber, String emailAddress, int age) {

    }

    public void removeEmployee(Employee userId) {

    }

    public void displayDetails() {

    }
}
