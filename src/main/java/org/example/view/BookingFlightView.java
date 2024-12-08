package org.example.view;

import org.example.controller.BookingFlightController;
import org.example.controller.DatabaseController;
import org.example.model.Client;
import org.example.model.Flight;
import org.example.model.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static org.example.controller.DatabaseController.*;

public class BookingFlightView extends JFrame {
    private static DatabaseController databaseController;
    private BookingFlightController controller;

    public BookingFlightView() {
        // Initialize the controller
        controller = new BookingFlightController();

        // Set up the frame
        setTitle("Flight Booking System");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs
        tabbedPane.addTab("Manage Flights", createManageFlightsPanel());
      //  tabbedPane.addTab("Manage Tickets", createManageTicketsPanel());
        tabbedPane.addTab("Purchase Tickets", createPurchaseTicketsPanel());

        // Add tabs to the frame
        add(tabbedPane, BorderLayout.CENTER);
    }
    private JPanel createManageFlightsPanel() {
        // Create the main panel with BoxLayout to allow vertical stacking
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create subpanels for flight details and buttons
        JPanel flightDetailsPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        flightDetailsPanel.setBorder(BorderFactory.createTitledBorder("Flight Details"));

        // Add flight components
        JLabel flightNumLabel = new JLabel("Flight Number:");
        JTextField flightNumField = new JTextField();
        JLabel flightTotalSeatNumLabel = new JLabel("Total Seats:");
        JTextField flightTotalSeatNumField = new JTextField();

        JLabel flightPriceLabel = new JLabel("Price:");
        JTextField flightPriceField = new JTextField();

        JLabel flightAirlineLabel = new JLabel("Airline:");
        JTextField flightAirlineField = new JTextField();

        JLabel flightDepartureLocLabel = new JLabel("Departure Location:");
        JTextField flightDepartureLocField = new JTextField();

        JLabel flightArrivalLocLabel = new JLabel("Arrival Location:");
        JTextField flightArrivalLocField = new JTextField();

        JLabel flightDepartureTimeLabel = new JLabel("Departure Time:");
        JTextField flightDepartureTimeField = new JTextField();

        JLabel flightArrivalTimeLabel = new JLabel("Arrival Time:");
        JTextField flightArrivalTimeField = new JTextField();

        // Add components to the flight details panel
        flightDetailsPanel.add(flightNumLabel);
        flightDetailsPanel.add(flightNumField);
        flightDetailsPanel.add(flightAirlineLabel);
        flightDetailsPanel.add(flightAirlineField);
        flightDetailsPanel.add(flightPriceLabel);
        flightDetailsPanel.add(flightPriceField);
        flightDetailsPanel.add(flightTotalSeatNumLabel);
        flightDetailsPanel.add(flightTotalSeatNumField);
        flightDetailsPanel.add(flightDepartureLocLabel);
        flightDetailsPanel.add(flightDepartureLocField);
        flightDetailsPanel.add(flightArrivalLocLabel);
        flightDetailsPanel.add(flightArrivalLocField);
        flightDetailsPanel.add(flightDepartureTimeLabel);
        flightDetailsPanel.add(flightDepartureTimeField);
        flightDetailsPanel.add(flightArrivalTimeLabel);
        flightDetailsPanel.add(flightArrivalTimeField);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centered buttons with spacing
        JButton addFlightButton = new JButton("Add Flight");
        JButton removeFlightButton = new JButton("Remove Flight");
        JButton viewFlightsButton = new JButton("View All Flights");

        // Create JTable for displaying flights
        String[] columnNames = {"Flight Number", "Airline", "Price", "Total Seats", "Departure Location", "Arrival Location", "Departure Time", "Arrival Time"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable flightTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(flightTable);
        scrollPane.setPreferredSize(new Dimension(600, 200));  // Set size for the table display
        panel.add(scrollPane);  // Add the scroll pane (with table) to the panel

        // Add action listeners
        addFlightButton.addActionListener(e -> {
            try {
                String flightNum = flightNumField.getText().trim();
                String airline = flightAirlineField.getText().trim();
                double price = Double.parseDouble(flightPriceField.getText().trim());
                int flightSeatNumber = Integer.parseInt(flightTotalSeatNumField.getText().trim());
                String departureLocation = flightDepartureLocField.getText().trim();
                String arrivalLocation = flightArrivalLocField.getText().trim();
                String departureTime = flightDepartureTimeField.getText().trim();
                String arrivalTime = flightArrivalTimeField.getText().trim();

                // Check if any field is empty
                if (flightNum.isEmpty() || airline.isEmpty() || departureLocation.isEmpty() || arrivalLocation.isEmpty() ||
                        departureTime.isEmpty() || arrivalTime.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                    return;
                }

                Flight flight = new Flight(flightNum, airline, price, flightSeatNumber, departureLocation, arrivalLocation, departureTime, arrivalTime);
                controller.addFlight(flight);
                JOptionPane.showMessageDialog(this, "Flight added successfully!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and seat number.");
            }
        });

        removeFlightButton.addActionListener(e -> {
            String flightNum = flightNumField.getText().trim();
            if (flightNum.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a flight number to remove.");
                return;
            }
            controller.removeFlight(flightNum);
            JOptionPane.showMessageDialog(this, "Flight removed successfully!");
        });

        // Update the JTable when viewing all flights
        viewFlightsButton.addActionListener(e -> {
            controller.viewAllFlights();  // Assuming a method that fetches all flights
            JOptionPane.showMessageDialog(this, "Flights for now are displaying in the console!");
        });

        // Add buttons to button panel
        buttonPanel.add(addFlightButton);
        buttonPanel.add(removeFlightButton);
        buttonPanel.add(viewFlightsButton);

        // Add panels to the main panel
        panel.add(flightDetailsPanel);
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createPurchaseTicketsPanel() {
        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10)); // 10 rows, 2 columns, 10px gap

        // Initialize labels and text fields
        JLabel flightNumLabel = new JLabel("Flight Number:");
        JTextField flightNumField = new JTextField(20);

        JLabel passNumLabel = new JLabel("Client Passport Number:");
        JTextField passNumField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);

        JLabel seatNumberLabel = new JLabel("Seat Number:");
        JTextField seatNumberField = new JTextField(20);

        JLabel departureLabel = new JLabel("Departure Date:");
        JTextField departureField = new JTextField(20);

        JLabel returnLabel = new JLabel("Return Date:");
        JTextField returnField = new JTextField(20);

        JLabel paymentTypeLabel = new JLabel("Payment Type (CREDIT/LOYALTY):");
        JTextField paymentTypeField = new JTextField(20);

        JLabel creditCardLabel = new JLabel("Credit Card Number:");
        JTextField creditCardField = new JTextField(20);

        JLabel ticketIdLabel = new JLabel("Ticket ID:");
        JTextField ticketIdField = new JTextField(20); // For canceling a ticket

        JButton purchaseButton = new JButton("Purchase Ticket");
        JButton cancelButton = new JButton("Cancel Ticket");

        // Add components to the panel
        panel.add(flightNumLabel);
        panel.add(flightNumField);

        panel.add(passNumLabel);
        panel.add(passNumField);

        panel.add(departureLabel);
        panel.add(departureField);

        panel.add(returnLabel);
        panel.add(returnField);

        panel.add(seatNumberLabel);
        panel.add(seatNumberField);

        panel.add(paymentTypeLabel);
        panel.add(paymentTypeField);

        panel.add(emailLabel);
        panel.add(emailField);

        panel.add(creditCardLabel);
        panel.add(creditCardField);

        panel.add(ticketIdLabel);
        panel.add(ticketIdField);

        // Add buttons
        panel.add(purchaseButton);
        panel.add(cancelButton);

        // Purchase ticket action listener
        purchaseButton.addActionListener(e -> {
            String seatNumber = seatNumberField.getText();
            String departureDate = departureField.getText();
            String returnDate = returnField.getText();
            String paymentType = paymentTypeField.getText();
            String email = emailField.getText();
            String creditCard = creditCardField.getText();

            // Find flight and client by the provided information
            Flight flight = findFlightByNumber(flightNumField.getText().trim());
            Client client = findClientByPassportNumber(passNumField.getText().trim());

            // Purchase flight ticket
            controller.purchaseFlightTicket(flight, client, seatNumber, departureDate, returnDate, paymentType, email, creditCard);
            Ticket ticket = new Ticket(flight, client, seatNumber, departureDate, returnDate, paymentType);
            controller.addTicket(ticket);

            // Show confirmation message
            JOptionPane.showMessageDialog(panel, "Ticket purchased successfully!");
        });

        // Cancel ticket action listener
        cancelButton.addActionListener(e -> {
            String ticketId = ticketIdField.getText().trim();
            Ticket ticket = DatabaseController.findTicketById(ticketId);

            if (ticket != null) {
                controller.cancelFlightTicket(ticket);
                JOptionPane.showMessageDialog(panel, "Ticket canceled successfully!");
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid Ticket ID!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Styling for buttons
        purchaseButton.setBackground(new Color(50, 205, 50));  // Green button
        purchaseButton.setForeground(Color.WHITE);
        purchaseButton.setFont(new Font("Arial", Font.BOLD, 14));

        cancelButton.setBackground(new Color(220, 20, 60));  // Red button
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));

        return panel;
    }

}
