package org.example.view;

import org.example.controller.DatabaseController;
import org.example.model.Ticket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientView extends JFrame {
    private String username; // To store the client's username

    public ClientView(String username) {
        this.username = username;

        // Frame settings
        setTitle("Client Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setBounds(50, 30, 300, 30);
        panel.add(welcomeLabel);

        // First Name Input
        JLabel firstNameLabel = new JLabel("Enter First Name:");
        firstNameLabel.setBounds(50, 80, 150, 30);
        panel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(200, 80, 150, 30);
        panel.add(firstNameField);

        // Last Name Input
        JLabel lastNameLabel = new JLabel("Enter Last Name:");
        lastNameLabel.setBounds(50, 120, 150, 30);
        panel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(200, 120, 150, 30);
        panel.add(lastNameField);

        // Display Bought Tickets Button
        JButton displayTicketsButton = new JButton("View Tickets");
        displayTicketsButton.setBounds(50, 180, 150, 30);
        displayTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the first and last name from the input fields
                String fName = firstNameField.getText();
                String lName = lastNameField.getText();

                if (fName.isEmpty() || lName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both first name and last name.");
                    return;
                }

                // Query the database using the provided names
                List<Ticket> tickets = DatabaseController.queryAllBoughtTicketsClient(fName, lName);

                if (tickets.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No tickets found for the provided names.");
                } else {
                    // Display the tickets
                    StringBuilder ticketsInfo = new StringBuilder("Your Tickets:\n");
                    for (Ticket ticket : tickets) {
                        ticketsInfo.append(ticket.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, ticketsInfo.toString());
                }
            }
        });
        panel.add(displayTicketsButton);

        // Add Review Button
        JButton addReviewButton = new JButton("Add Review");
        addReviewButton.setBounds(50, 230, 150, 30);
        addReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Input dialog for review details
                String email = JOptionPane.showInputDialog("Enter your email:");
                String title = JOptionPane.showInputDialog("Enter review title:");
                String body = JOptionPane.showInputDialog("Enter review body:");

                if (email != null && title != null && body != null && !email.isEmpty() && !title.isEmpty() && !body.isEmpty()) {
                    if (DatabaseController.insertReview(email, title, body)) { // Example method
                        JOptionPane.showMessageDialog(null, "Review added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add review. Try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "All fields are required!");
                }
            }
        });
        panel.add(addReviewButton);

        // Display All Reviews Button
        JButton displayReviewsButton = new JButton("View Reviews");
        displayReviewsButton.setBounds(50, 280, 150, 30);
        displayReviewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to display reviews (Fetch from database)
                String reviews = DatabaseController.queryAllReviews().toString(); // Example method
                JOptionPane.showMessageDialog(null, "All Reviews:\n" + reviews);
            }
        });
        panel.add(displayReviewsButton);

        // Logout Button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBounds(50, 330, 150, 30);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomeView().setVisible(true); // Return to Welcome View
                dispose(); // Close current window
            }
        });
        panel.add(logoutButton);

        add(panel);
        setVisible(true);
    }
}