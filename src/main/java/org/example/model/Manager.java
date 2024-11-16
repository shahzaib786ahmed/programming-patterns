package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Manager extends Employee{

    private static List<Employee> employees;

    public Manager(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age, discountRate);
    }

    /**
     *
     * @param newEmployee
     * @return
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
     *
     * @param employee
     */
    //TODO:do we remove here or do we add it in ticketsystem
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    /**
     *
     */
    @Override
    public void displayDetails() {
        System.out.println("Manager: " + super.toString());
    }
}
