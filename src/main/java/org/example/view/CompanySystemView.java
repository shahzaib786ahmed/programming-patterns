package org.example.view;

import org.example.controller.CompanySystemController;
import org.example.controller.DatabaseController;
import org.example.model.Client;
import org.example.model.Employee;
import org.example.model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanySystemView extends JFrame {
    private CompanySystemController controller;

    public CompanySystemView(CompanySystemController controller) {
        this.controller = controller;
        setTitle("Company System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize UI components
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));

        JButton addClientButton = new JButton("Add Client");
        JButton addEmployeeButton = new JButton("Add Employee");
        JButton addManagerButton = new JButton("Add Manager");

        JButton deleteClientButton = new JButton("Delete Client");
        JButton deleteEmployeeButton = new JButton("Delete Employee");
        JButton deleteManagerButton = new JButton("Delete Manager");

        JButton displayClientsButton = new JButton("Display Clients");
        JButton displayEmployeesButton = new JButton("Display Employees");
        JButton displayManagersButton = new JButton("Display Managers");

        // Add buttons to the panel
        buttonPanel.add(addClientButton);
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(addManagerButton);

        buttonPanel.add(deleteClientButton);
        buttonPanel.add(deleteEmployeeButton);
        buttonPanel.add(deleteManagerButton);

        buttonPanel.add(displayClientsButton);
        buttonPanel.add(displayEmployeesButton);
        buttonPanel.add(displayManagersButton);

        // Add action listeners for buttons
        addClientButton.addActionListener(e -> controller.addClient(promptForClientDetails()));
        addEmployeeButton.addActionListener(e -> controller.addEmployee(promptForEmployeeDetails()));
        addManagerButton.addActionListener(e -> controller.addManager(promptForManagerDetails()));

        deleteClientButton.addActionListener(e -> controller.deleteClient(promptForClientId()));
        deleteEmployeeButton.addActionListener(e -> controller.deleteEmployee(promptForEmployeeId()));
        deleteManagerButton.addActionListener(e -> controller.deleteManager(promptForManagerId()));

        displayClientsButton.addActionListener(e -> controller.displayClients());
        displayEmployeesButton.addActionListener(e -> controller.displayEmployee());
        displayManagersButton.addActionListener(e -> controller.displayManagers());

        add(buttonPanel, BorderLayout.CENTER);
    }

    private Client promptForClientDetails() {
        String fName = JOptionPane.showInputDialog(this, "Enter Client First Name:");
        String lName = JOptionPane.showInputDialog(this, "Enter Client last Name:");
        String passNum = JOptionPane.showInputDialog(this, "Enter Client Passport Number:");

        String phone = JOptionPane.showInputDialog(this, "Enter Client Phone Number:");
        String email = JOptionPane.showInputDialog(this, "Enter Client Email:");
        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Client age:"));
        String username = JOptionPane.showInputDialog(this, "Enter Client username:");
        String password= JOptionPane.showInputDialog(this, "Enter Client Password:");


        return new Client(lName,fName,passNum,phone,email,age,username,password,0); // Assumes Client has this constructor.
    }

    private Employee promptForEmployeeDetails() {
        String lname = JOptionPane.showInputDialog(this, "Enter Employee Last Name:");
        String fname = JOptionPane.showInputDialog(this, "Enter Employee First Name:");
        String passNum = JOptionPane.showInputDialog(this, "Enter Employee Passport Number:");
        String phone = JOptionPane.showInputDialog(this, "Enter Employee Phone Number:");
        String email = JOptionPane.showInputDialog(this, "Enter Employee Email:");
        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Employee age:"));
        String username = JOptionPane.showInputDialog(this, "Enter Employee username:");
        String password= JOptionPane.showInputDialog(this, "Enter Employee Password:");
        double discount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Employee Discount:"));

        return new Employee(lname,fname,passNum,phone,email,age,username,password,0.5); // Assumes Employee has this constructor.
    }

    private Manager promptForManagerDetails() {
        String lname = JOptionPane.showInputDialog(this, "Enter Manager Last Name:");
        String fname = JOptionPane.showInputDialog(this, "Enter Manager First Name:");
        String passNum = JOptionPane.showInputDialog(this, "Enter Manager Passport Number:");
        String phone = JOptionPane.showInputDialog(this, "Enter Manager Phone Number:");
        String email = JOptionPane.showInputDialog(this, "Enter Manager Email:");
        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Manager age:"));
        String username = JOptionPane.showInputDialog(this, "Enter Manager username:");
        String password= JOptionPane.showInputDialog(this, "Enter Manager Password:");
        double discount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Manager Discount:"));

        return new Manager(lname,fname,passNum,phone,email,age,username,password,0.7); // Assumes Employee has this constructor.
    }

    private Client promptForClientId() {
        String passNum = JOptionPane.showInputDialog(this, "Enter Client Passport Number to delete:");
        return DatabaseController.findClientByPassportNumber(passNum); // Assumes Client can be constructed with an ID for deletion purposes.
    }

    private Employee promptForEmployeeId() {
        String passNum = JOptionPane.showInputDialog(this, "Enter Employee Passport Number to delete:");
        return DatabaseController.queryEmployeeByPassport(passNum); // Assumes Employee can be constructed with an ID for deletion purposes.
    }

    private Manager promptForManagerId() {
        String passNum = JOptionPane.showInputDialog(this, "Enter Manager Passport Number to delete:");
        return DatabaseController.queryManagerByPassportNumber(passNum); // Assumes Manager can be constructed with an ID for deletion purposes.
    }

}
