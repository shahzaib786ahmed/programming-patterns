package org.example.view;

import org.example.controller.BookingFlightController;
import org.example.controller.DatabaseController;
import org.example.model.Flight;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;


public class BookingFlightView extends JFrame {
    private static DatabaseController databaseController;
    private BookingFlightController controller;
    private ResourceBundle bundle;

    /**
     * Constructs a BookingFlightView instance, initializing the GUI for managing flight bookings and ticket purchases.
     * The interface is localized based on the provided locale.
     *
     * @param currentLocale the Locale to use for localization of the GUI text.
     *                      This determines the language and regional formatting of the interface.
     */
    public BookingFlightView(Locale currentLocale) {
        // Load resource bundle based on the locale
        bundle = ResourceBundle.getBundle("messages", currentLocale);

        // Initialize the controller
        controller = new BookingFlightController();

        // Set up the frame
        setTitle(bundle.getString("flightBookingSystemTitle"));
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs with localized titles
        tabbedPane.addTab(bundle.getString("manageFlightsTab"), createManageFlightsPanel());
        tabbedPane.addTab(bundle.getString("purchaseTicketsTab"), createPurchaseTicketsPanel());

        // Add tabs to the frame
        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Create a manage flight panel
     *
     * @return a panel
     */
    private JPanel createManageFlightsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel flightDetailsPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        flightDetailsPanel.setBorder(BorderFactory.createTitledBorder(bundle.getString("flightDetailsTitle")));

        // Add localized flight details labels and fields
        JLabel flightNumLabel = new JLabel(bundle.getString("flightNumberLabel"));
        JTextField flightNumField = new JTextField();
        JLabel flightTotalSeatNumLabel = new JLabel(bundle.getString("totalSeatsLabel"));
        JTextField flightTotalSeatNumField = new JTextField();
        JLabel flightPriceLabel = new JLabel(bundle.getString("priceLabel"));
        JTextField flightPriceField = new JTextField();
        JLabel flightAirlineLabel = new JLabel(bundle.getString("airlineLabel"));
        JTextField flightAirlineField = new JTextField();
        JLabel flightDepartureLocLabel = new JLabel(bundle.getString("departureLocationLabel"));
        JTextField flightDepartureLocField = new JTextField();
        JLabel flightArrivalLocLabel = new JLabel(bundle.getString("arrivalLocationLabel"));
        JTextField flightArrivalLocField = new JTextField();
        JLabel flightDepartureTimeLabel = new JLabel(bundle.getString("departureTimeLabel"));
        JTextField flightDepartureTimeField = new JTextField();
        JLabel flightArrivalTimeLabel = new JLabel(bundle.getString("arrivalTimeLabel"));
        JTextField flightArrivalTimeField = new JTextField();

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addFlightButton = new JButton(bundle.getString("addFlightButton"));
        JButton removeFlightButton = new JButton(bundle.getString("removeFlightButton"));
        JButton viewFlightsButton = new JButton(bundle.getString("viewFlightsButton"));

        // JTable for displaying flights
        String[] columnNames = {
                bundle.getString("flightNumberColumn"),
                bundle.getString("airlineColumn"),
                bundle.getString("priceColumn"),
                bundle.getString("totalSeatsColumn"),
                bundle.getString("departureLocationColumn"),
                bundle.getString("arrivalLocationColumn"),
                bundle.getString("departureTimeColumn"),
                bundle.getString("arrivalTimeColumn")
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable flightTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(flightTable);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        panel.add(scrollPane);

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

                if (flightNum.isEmpty() || airline.isEmpty() || departureLocation.isEmpty() || arrivalLocation.isEmpty() ||
                        departureTime.isEmpty() || arrivalTime.isEmpty()) {
                    JOptionPane.showMessageDialog(this, bundle.getString("fillAllFieldsMessage"));
                    return;
                }

                Flight flight = new Flight(flightNum, airline, price, flightSeatNumber, departureLocation, arrivalLocation, departureTime, arrivalTime);
                controller.addFlight(flight);
                JOptionPane.showMessageDialog(this, bundle.getString("flightAddedMessage"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, bundle.getString("invalidNumberMessage"));
            }
        });

        removeFlightButton.addActionListener(e -> {
            String flightNum = flightNumField.getText().trim();
            if (flightNum.isEmpty()) {
                JOptionPane.showMessageDialog(this, bundle.getString("enterFlightNumberMessage"));
                return;
            }
            controller.removeFlight(flightNum);
            JOptionPane.showMessageDialog(this, bundle.getString("flightRemovedMessage"));
        });

        viewFlightsButton.addActionListener(e -> {
            // Clear existing rows in the table
            model.setRowCount(0);

            // Fetch the list of flights from the database or controller
            List<Flight> flights = DatabaseController.queryAllFlight();

            // Check if flights exist
            if (flights.isEmpty()) {
                JOptionPane.showMessageDialog(panel, bundle.getString("noFlightsMessage"));
            } else {
                // Add each flight's details as a row in the table
                for (Flight flight : flights) {
                    model.addRow(new Object[]{
                            flight.getFlightNumber(),
                            flight.getAirline(),
                            flight.getPrice(),
                            flight.getFlightSeatNumber(),
                            flight.getDepartureLocation(),
                            flight.getArrivalLocation(),
                            flight.getDepartureTime(),
                            flight.getArrivalTime()
                    });
                }
                JOptionPane.showMessageDialog(panel, bundle.getString("flightsLoadedMessage"));
            }
        });

        buttonPanel.add(addFlightButton);
        buttonPanel.add(removeFlightButton);
        buttonPanel.add(viewFlightsButton);

        panel.add(flightDetailsPanel);
        panel.add(buttonPanel);

        return panel;
    }

    /**
     * Create a purchase ticket panel
     *
     * @return a panel
     */
    private JPanel createPurchaseTicketsPanel() {
        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));

        JLabel flightNumLabel = new JLabel(bundle.getString("flightNumberLabel"));
        JTextField flightNumField = new JTextField(20);
        JLabel passNumLabel = new JLabel(bundle.getString("clientPassportNumberLabel"));
        JTextField passNumField = new JTextField(20);
        JLabel emailLabel = new JLabel(bundle.getString("emailLabel"));
        JTextField emailField = new JTextField(20);
        JLabel seatNumberLabel = new JLabel(bundle.getString("seatNumberLabel"));
        JTextField seatNumberField = new JTextField(20);
        JLabel departureLabel = new JLabel(bundle.getString("departureDateLabel"));
        JTextField departureField = new JTextField(20);
        JLabel returnLabel = new JLabel(bundle.getString("returnDateLabel"));
        JTextField returnField = new JTextField(20);
        JLabel paymentTypeLabel = new JLabel(bundle.getString("paymentTypeLabel"));
        JTextField paymentTypeField = new JTextField(20);
        JLabel creditCardLabel = new JLabel(bundle.getString("creditCardLabel"));
        JTextField creditCardField = new JTextField(20);

        JButton purchaseButton = new JButton(bundle.getString("purchaseTicketButton"));
        JButton cancelButton = new JButton(bundle.getString("cancelTicketButton"));

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

        panel.add(purchaseButton);
        panel.add(cancelButton);

        // Add action listeners
        purchaseButton.addActionListener(e -> {
            // Add purchase ticket logic
            JOptionPane.showMessageDialog(panel, bundle.getString("ticketPurchasedMessage"));
        });

        cancelButton.addActionListener(e -> {
            // Add cancel ticket logic
            JOptionPane.showMessageDialog(panel, bundle.getString("ticketCanceledMessage"));
        });

        return panel;
    }
}
