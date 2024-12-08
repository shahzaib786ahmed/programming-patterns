package org.example.view;

import org.example.controller.BookingHotelController;
import org.example.controller.DatabaseController;
import org.example.model.Client;
import org.example.model.Hotel;
import org.example.model.Room;

import javax.swing.*;
import java.awt.*;

public class BookingHotelView extends JFrame {

    private BookingHotelController controller;

    public BookingHotelView() {
        // Initialize the controller
        controller = new BookingHotelController();

        // Set up the JFrame
        setTitle("Hotel Booking System");
        setSize(600, 650); // Increased height for better visibility of buttons
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Use BoxLayout for vertical layout

        // Add components
        add(createButtonPanel());
        add(Box.createRigidArea(new Dimension(0, 10))); // Adds spacing between panels
        add(createRoomBookingPanel());
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(createHotelManagementPanel());
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(createRoomManagementPanel());
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(createViewPanel());

        // Make the frame visible
        setVisible(true);
    }

    /**
     * Creates a panel with buttons for common actions.
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton viewHotelsButton = new JButton("View All Hotels");
        JButton viewRoomsButton = new JButton("View All Rooms");

        viewHotelsButton.addActionListener(e -> controller.viewAllHotels());
        viewRoomsButton.addActionListener(e -> controller.viewAllRooms());

        buttonPanel.add(viewHotelsButton);
        buttonPanel.add(viewRoomsButton);

        return buttonPanel;
    }

    /**
     * Creates a panel for room booking actions.
     */
    private JPanel createRoomBookingPanel() {
        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS)); // Arrange vertically

        JLabel clientLabel = new JLabel("Client Passport Number:");
        JTextField clientField = new JTextField(10);

        JLabel roomNumLabel = new JLabel("Room Number:");
        JTextField roomNumField = new JTextField(5);

        JLabel nightsLabel = new JLabel("Nights:");
        JTextField nightsField = new JTextField(3);

        JLabel cardLabel = new JLabel("Credit Card:");
        JTextField cardField = new JTextField(16);

        JButton bookRoomButton = new JButton("Book Room");

        bookRoomButton.addActionListener(e -> {
            try {
                String passNum = clientField.getText();
                int roomNum = Integer.parseInt(roomNumField.getText());
                int nights = Integer.parseInt(nightsField.getText());
                String creditCard = cardField.getText();

                // Fetch Room and Client objects from the database
                Room room = DatabaseController.findRoomByNumber(roomNum);
                Client client = DatabaseController.findClientByPassportNumber(passNum);

                if (room != null && client != null) {
                    boolean success = controller.bookRoom(client, room, nights, creditCard);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Room booked successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Room booking failed.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid client or room information.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of nights.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
            }
        });

        bookingPanel.add(clientLabel);
        bookingPanel.add(clientField);
        bookingPanel.add(roomNumLabel);
        bookingPanel.add(roomNumField);
        bookingPanel.add(nightsLabel);
        bookingPanel.add(nightsField);
        bookingPanel.add(cardLabel);
        bookingPanel.add(cardField);
        bookingPanel.add(bookRoomButton);

        return bookingPanel;
    }

    /**
     * Creates a panel for hotel management actions.
     */
    private JPanel createHotelManagementPanel() {
        JPanel hotelPanel = new JPanel();
        hotelPanel.setLayout(new BoxLayout(hotelPanel, BoxLayout.Y_AXIS));

        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        JTextField hotelNameField = new JTextField(10);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(15);

        JLabel totalRoomsLabel = new JLabel("Total Rooms:");
        JTextField totalRoomsField = new JTextField(5);

        JButton addHotelButton = new JButton("Add Hotel");
        JButton removeHotelButton = new JButton("Remove Hotel");

        addHotelButton.addActionListener(e -> {
            try {
                String hotelName = hotelNameField.getText();
                String address = addressField.getText();
                int totalRooms = Integer.parseInt(totalRoomsField.getText());

                if (hotelName.isEmpty() || address.isEmpty() || totalRooms <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter valid hotel details.");
                    return;
                }

                Hotel hotel = new Hotel(totalRooms, address, hotelName);
                boolean success = controller.addHotel(hotel);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Hotel added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add hotel. It might already exist.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Total rooms must be a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
            }
        });

        removeHotelButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText();

            if (hotelName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid hotel name.");
                return;
            }

            Hotel hotel = (Hotel) DatabaseController.findHotelByName(hotelName);
            if (hotel != null) {
                boolean success = controller.removeHotel(hotel);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Hotel removed successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove hotel.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hotel not found.");
            }
        });

        hotelPanel.add(hotelNameLabel);
        hotelPanel.add(hotelNameField);
        hotelPanel.add(addressLabel);
        hotelPanel.add(addressField);
        hotelPanel.add(totalRoomsLabel);
        hotelPanel.add(totalRoomsField);
        hotelPanel.add(addHotelButton);
        hotelPanel.add(removeHotelButton);

        return hotelPanel;
    }

    /**
     * Creates a panel for room management actions.
     */
    private JPanel createRoomManagementPanel() {
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));

        JLabel roomNumLabel = new JLabel("Room Number:");
        JTextField roomNumField = new JTextField(5);

        JLabel capacityLabel = new JLabel("Capacity:");
        JTextField capacityField = new JTextField(5);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(7);

        JButton addRoomButton = new JButton("Add Room");
        JButton deleteRoomButton = new JButton("Delete Room");

        addRoomButton.addActionListener(e -> {
            try {
                int roomNum = Integer.parseInt(roomNumField.getText());
                int capacity = Integer.parseInt(capacityField.getText());
                double price = Double.parseDouble(priceField.getText());

                Room room = new Room(roomNum, capacity, Room.RoomStatus.AVAILABLE, price);
                controller.addRooms(room);
                JOptionPane.showMessageDialog(this, "Room added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid capacity and price values.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
            }
        });

        deleteRoomButton.addActionListener(e -> {
            int roomNum = Integer.parseInt(roomNumField.getText());
            Room room = DatabaseController.findRoomByNumber(roomNum);

            if (room != null) {
                controller.removeRoom(room);
                JOptionPane.showMessageDialog(this, "Room deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Room not found.");
            }
        });

        roomPanel.add(roomNumLabel);
        roomPanel.add(roomNumField);
        roomPanel.add(capacityLabel);
        roomPanel.add(capacityField);
        roomPanel.add(priceLabel);
        roomPanel.add(priceField);
        roomPanel.add(addRoomButton);
        roomPanel.add(deleteRoomButton);

        return roomPanel;
    }

    /**
     * Creates a panel for view operations.
     */
    private JPanel createViewPanel() {
        JPanel viewPanel = new JPanel(new FlowLayout());

        JButton viewAvailableRoomsButton = new JButton("View Available Rooms");

        viewAvailableRoomsButton.addActionListener(e -> controller.viewAllRooms());

        viewPanel.add(viewAvailableRoomsButton);

        return viewPanel;
    }
}
