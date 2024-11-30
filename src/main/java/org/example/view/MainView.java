package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
@Getter
public class MainView {
    private JFrame frame;
    private JButton addClientButton, addEmployeeButton, addManagerButton,
            deleteClientButton, deleteEmployeeButton, deleteManagerButton,
            viewClientsButton, viewEmployeesButton, viewManagersButton,
            viewReviewsButton, viewHotelsButton, viewFlightsButton, viewTicketsButton, viewRoomsButton;

    public MainView() {
        // Initialize the main JFrame
        frame = new JFrame("Travel Agency System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Initialize buttons
        addClientButton = new JButton("Add Client");
        addEmployeeButton = new JButton("Add Employee");
        addManagerButton = new JButton("Add Manager");
        deleteClientButton = new JButton("Delete Client");
        deleteEmployeeButton = new JButton("Delete Employee");
        deleteManagerButton = new JButton("Delete Manager");
        viewClientsButton = new JButton("View Clients");
        viewEmployeesButton = new JButton("View Employees");
        viewManagersButton = new JButton("View Managers");
        viewReviewsButton = new JButton("View Reviews");
        viewFlightsButton = new JButton("View Flights");
        viewHotelsButton = new JButton("View Hotels");
        viewRoomsButton = new JButton("View Rooms");
        viewTicketsButton = new JButton("View Bought Tickets");

        // Layout setup
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10)); // Vertical layout with spacing

        // Add buttons to the panel
        panel.add(addManagerButton);
        panel.add(addEmployeeButton);
        panel.add(addClientButton);
        panel.add(deleteClientButton);
        panel.add(deleteEmployeeButton);
        panel.add(deleteManagerButton);
        panel.add(viewClientsButton);
        panel.add(viewEmployeesButton);
        panel.add(viewManagersButton);
        panel.add(viewReviewsButton);
        panel.add(viewHotelsButton);
        panel.add(viewFlightsButton);
        panel.add(viewTicketsButton);
        panel.add(viewRoomsButton);

        // Add panel to frame
        frame.add(panel, BorderLayout.CENTER);
    }

    // Method to display the JFrame
    public void show() {
        frame.setVisible(true);
    }
}



