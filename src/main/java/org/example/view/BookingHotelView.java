package org.example.view;

import org.example.controller.BookingHotelController;
import org.example.controller.DatabaseController;
import org.example.model.Client;
import org.example.model.Hotel;
import org.example.model.Room;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class BookingHotelView extends JFrame {

    private BookingHotelController controller;
    private ResourceBundle bundle;

    /**
     * Constructs a new `BookingHotelView` instance, initializing the frame,
     * loading the appropriate resource bundle based on the specified locale,
     * setting up the GUI components, and displaying the window.
     *
     * @param currentLocale The locale that determines the language and region
     *                      for the UI strings.
     */
    public BookingHotelView(Locale currentLocale) {
        // Load resource bundle based on the locale
        bundle = ResourceBundle.getBundle("messages", currentLocale);

        // Initialize the controller
        controller = new BookingHotelController();

        // Set up the frame
        setTitle(bundle.getString("hotelBookingSystemTitle"));
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs with localized titles
        tabbedPane.addTab(bundle.getString("roomBookingTab"), createRoomBookingPanel());
        tabbedPane.addTab(bundle.getString("hotelManagementTab"), createHotelManagementPanel());
        tabbedPane.addTab(bundle.getString("roomManagementTab"), createRoomManagementPanel());
        tabbedPane.addTab(bundle.getString("viewPanelTab"), createViewPanel());

        // Add tabs to the frame
        add(tabbedPane, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    /**
     * Create panel button
     * @return a panel
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton viewHotelsButton = new JButton(bundle.getString("view.all.hotels"));
        JButton viewRoomsButton = new JButton(bundle.getString("view.all.rooms"));

        viewHotelsButton.addActionListener(e -> controller.viewAllHotels());
        viewRoomsButton.addActionListener(e -> controller.viewAllRooms());

        buttonPanel.add(viewHotelsButton);
        buttonPanel.add(viewRoomsButton);

        return buttonPanel;
    }

    /**
     * Creates a room booking panel
     * @return a panel
     */
    private JPanel createRoomBookingPanel() {
        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS)); // Arrange vertically

        JLabel clientLabel = new JLabel(bundle.getString("client.passport.number"));
        JTextField clientField = new JTextField(10);

        JLabel roomNumLabel = new JLabel(bundle.getString("room.number"));
        JTextField roomNumField = new JTextField(5);

        JLabel nightsLabel = new JLabel(bundle.getString("nights"));
        JTextField nightsField = new JTextField(3);

        JLabel cardLabel = new JLabel(bundle.getString("credit.card"));
        JTextField cardField = new JTextField(16);

        JButton bookRoomButton = new JButton(bundle.getString("book.room"));

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
                        JOptionPane.showMessageDialog(this, bundle.getString("room.booked.successfully"));
                    } else {
                        JOptionPane.showMessageDialog(this, bundle.getString("room.booking.failed"));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, bundle.getString("invalid.client.room"));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, bundle.getString("room.booking.failed"));
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
     * Creates a hotel management panel
     * @return a panel
     */
    private JPanel createHotelManagementPanel() {
        JPanel hotelPanel = new JPanel();
        hotelPanel.setLayout(new BoxLayout(hotelPanel, BoxLayout.Y_AXIS));

        JLabel hotelNameLabel = new JLabel(bundle.getString("hotel.name"));
        JTextField hotelNameField = new JTextField(10);

        JLabel addressLabel = new JLabel(bundle.getString("address"));
        JTextField addressField = new JTextField(15);

        JLabel totalRoomsLabel = new JLabel(bundle.getString("total.rooms"));
        JTextField totalRoomsField = new JTextField(5);

        JButton addHotelButton = new JButton(bundle.getString("add.hotel"));
        JButton removeHotelButton = new JButton(bundle.getString("remove.hotel"));

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
     * Creates a room management panel
     * @return a panel
     */
    private JPanel createRoomManagementPanel() {
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));

        JLabel roomNumLabel = new JLabel(bundle.getString("room.number"));
        JTextField roomNumField = new JTextField(5);

        JLabel capacityLabel = new JLabel(bundle.getString("capacity"));
        JTextField capacityField = new JTextField(5);

        JLabel priceLabel = new JLabel(bundle.getString("price"));
        JTextField priceField = new JTextField(7);

        JButton addRoomButton = new JButton(bundle.getString("add.room"));
        JButton deleteRoomButton = new JButton(bundle.getString("delete.room"));

        addRoomButton.addActionListener(e -> {
            try {
                int roomNum = Integer.parseInt(roomNumField.getText());
                int capacity = Integer.parseInt(capacityField.getText());
                double price = Double.parseDouble(priceField.getText());

                Room room = new Room(roomNum, capacity, Room.RoomStatus.AVAILABLE, price);
                controller.addRooms(room);
                JOptionPane.showMessageDialog(this, bundle.getString("room.added.successfully"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, bundle.getString("invalid.room.details"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
            }
        });

        deleteRoomButton.addActionListener(e -> {
            int roomNum = Integer.parseInt(roomNumField.getText());
            Room room = DatabaseController.findRoomByNumber(roomNum);

            if (room != null) {
                controller.removeRoom(room);
                JOptionPane.showMessageDialog(this, bundle.getString("room.deleted.successfully"));
            } else {
                JOptionPane.showMessageDialog(this, bundle.getString("room.not.found"));
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
     * Create a view panel
     * @return a panel
     */
    private JPanel createViewPanel() {
        JPanel viewPanel = new JPanel(new FlowLayout());

        JButton viewAvailableRoomsButton = new JButton(bundle.getString("view.available.rooms"));

        viewAvailableRoomsButton.addActionListener(e -> controller.viewAllRooms());

        viewPanel.add(viewAvailableRoomsButton);

        return viewPanel;
    }
}
