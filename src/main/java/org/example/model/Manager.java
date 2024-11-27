package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Manager extends Employee {

    private static List<Employee> employees;

    public Manager(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age, discountRate);
    }
//TODO: ADD/REMOVE METHOD IN CONTROLLER
    /**
     * Adds a new employee to the agency
     * @param newEmployee to be added at the agency
     * @return the new employee added
     */
    //TODO:need to choose how to add employe here or in ticket system class
    public Employee addEmployee(Employee newEmployee) {
        if (employees.contains(newEmployee)) {
            throw new IllegalArgumentException("Employee already exists.");
        } else {
            employees.add(newEmployee);
            return newEmployee;
        }
    }

    /**
     * Removes an employee from the agency
     * @param employee to be removed from the agency
     */
    //TODO:do we remove here or do we add it in ticketsystem
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    /**
     * Display the details of the manager
     */
    @Override
    public void displayDetails() {
        System.out.println("Manager: " + super.toString());
    }
}
