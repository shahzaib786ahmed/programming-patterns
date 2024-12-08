package org.example.controller;

import org.example.model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookingHotelController {
    private HotelSystem hotelSystem;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public BookingHotelController() {
        this.hotelSystem = HotelSystem.getInstance();
        initTables();
    }

    /**
     * Initializes the hotel and room tables in the database.
     */
    private void initTables() {
        DatabaseController.createHotelTable();
        DatabaseController.createRoomTable();
    }

    /**
     * Adds a hotel to the system and inserts it into the database.
     *
     * @param hotel the hotel to be added
     * @return
     */
    public boolean addHotel(Hotel hotel) {
        threadPool.submit(() -> {
            DatabaseController.insertHotel(hotel);
            HotelSystem.getHotels().add(hotel);
            return true;
        });
        return false;
    }

    /**
     * Removes a hotel from the system and deletes it from the database.
     *
     * @param hotel the hotel to be removed
     * @return
     */
    public boolean removeHotel(Hotel hotel) {
        threadPool.submit(() -> {
            DatabaseController.deleteHotel(hotel.getHotel_id());
            HotelSystem.getHotels().remove(hotel);
            return true;
        });
        return false;
    }

    /**
     * Retrieves and displays all hotels in the system.
     */
    public void viewAllHotels() {
        threadPool.submit(() -> {
            DatabaseController.queryAllHotels();
        });
    }

    /**
     * Adds a room to the system and inserts it into the database.
     *
     * @param room the room to be added
     */
    public void addRooms(Room room) {
        threadPool.submit(() -> {
            DatabaseController.insertRoom(room);
            HotelSystem.getAvailableRooms().add(room);
        });
    }

    /**
     * Removes a room from the system and deletes it from the database.
     *
     * @param room the room to be removed
     */
    public void removeRoom(Room room) {
        threadPool.submit(() -> {
            DatabaseController.deleteRoom(room.getRoomNum());
            HotelSystem.getAvailableRooms().remove(room);
        });
    }


    /**
     * Retrieves and displays all rooms in the system.
     */
    public void viewAllRooms() {
        threadPool.submit(() -> {
            DatabaseController.queryAllRooms();
        });
    }

    /**
     * Sends a refund confirmation email to the recipient.
     *
     * @param recipientEmail the recipient's email address
     * @param subject        the subject of the email
     * @param messageBody    the body of the email
     */
    private static void sendRefundConfirmation(String recipientEmail, String subject, String messageBody) {
        threadPool.submit(() -> {
            System.out.println("Sending refund email to: " + recipientEmail);
            System.out.println("Subject: " + subject);
            System.out.println("Message: " + messageBody);
            System.out.println("Refund confirmation email sent successfully.");
        });
    }

    /**
     * Refounds payment when a booking is canceled.
     *
     * @param room             the room for which the payment is refunded
     * @param creditCardNumber the credit card where the payment is refunded
     */
    private static void refundPayment(Room room, String creditCardNumber) {
        threadPool.submit(() -> {
            Client client = room.getClient();
            double refundAmount = room.getPrice();
            if (isValidCreditCardFormat(creditCardNumber)) {
                System.out.println("Refund of $" + refundAmount + " processed to credit card for client: " + client.getFName() + " " + client.getLName());
                TicketSystem.getPaymentHistory().add("Refund processed for: " + client.getLName() + ", " + client.getFName() + " for the amount of: " + refundAmount + " with credit card: " + creditCardNumber);
            } else {
                System.out.println("Refund failed: Invalid payment type.");
            }

            // Send refund confirmation to the client
            sendRefundConfirmation(client.getEmailAddress(), "Refund Confirmation", "Your payment of $" + refundAmount + " has been refunded.");
        });
    }

    /**
     * Books a hotel room for the client after payment is processed.
     *
     * @param client         that wants to book a room
     * @param room           to be booked for the client
     * @param numberOfNights number of nights staying at the hotel
     * @return boolean indicating if the room was successfully booked
     */
    public boolean bookRoom(Client client, Room room, int numberOfNights, String creditCardNumber) {
        threadPool.submit(() -> {
            if (room.getRoomStatus() == Room.RoomStatus.AVAILABLE) {
                // Calculate room price based on number of nights
                double totalPrice = numberOfNights * room.getPrice();
                room.setPrice(totalPrice);

                // Process payment
                boolean paymentSuccessful = processPayment(client, totalPrice, creditCardNumber);
                if (paymentSuccessful) {
                    // Mark the room as reserved if payment is successful
                    room.setRoomStatus(Room.RoomStatus.RESERVED);
                    room.setClient(client);
                    HotelSystem.getAvailableRooms().remove(room);
                    HotelSystem.getReservedRooms().add(room);
                    DatabaseController.insertRoom(room);

                    System.out.println("Room " + room.getRoomNum() + " booked successfully for " + client.getFName() + " " + client.getLName() + " with credit card ending with: " + creditCardNumber.substring(creditCardNumber.length() - 4));
                    TicketSystem.paymentHistory.add(client.getLName() + ", " + client.getFName() + " | Amount Paid: " + totalPrice + " | Credit card used: " + creditCardNumber);
                } else {
                    System.out.println("Payment failed for client " + client.getFName() + " " + client.getLName() + ". Booking not confirmed!");
                }
            } else {
                System.out.println("Room " + room.getRoomNum() + " is not available for booking.");
            }
        });

        // Return true or false based on whether the room was successfully booked
        return room.getRoomStatus() == Room.RoomStatus.RESERVED;
    }

    /**
     * Processes the payment for the room booking.
     *
     * @param client           the client making the booking
     * @param amount           the total price of the room booking
     * @param creditCardNumber the client's credit card number
     * @return true if payment is successful, false otherwise
     */
    private boolean processPayment(Client client, double amount, String creditCardNumber) {
        return processCreditCardPayment(creditCardNumber, amount);
    }

    /**
     * Validates the credit card number format.
     * This checks if the credit card number contains only digits
     * and if its length is between 13 and 19 characters.
     *
     * @param creditCardNumber the credit card number to validate
     * @return true if the card number is valid, false otherwise
     */
    private static boolean isValidCreditCardFormat(String creditCardNumber) {
        // Remove spaces or hyphens from the credit card number
        String cleanedCardNumber = creditCardNumber.replaceAll("[^0-9]", "");

        // Check if the cleaned card number contains only digits and has a length between 13 and 19
        return cleanedCardNumber.matches("[0-9]+") && cleanedCardNumber.length() >= 15 && cleanedCardNumber.length() <= 19;

    }

    /**
     * Simulates the process of making a credit card payment.
     *
     * @param creditCardNumber the credit card number for payment
     * @param amount           the amount to be charged to the credit card
     * @return true if payment was successful, false otherwise
     */
    private boolean processCreditCardPayment(String creditCardNumber, double amount) {
        // Check if the credit card format is valid
        if (!isValidCreditCardFormat(creditCardNumber)) {
            System.out.println("Invalid credit card format. Payment failed.");
            return false;  // Invalid format, payment fails
        }
        System.out.println("Credit card payment of " + amount + " processed sucessfully");
        return true;  // Payment successful
    }

    /**
     * Cancels a hotel room for a customer.
     *
     * @param room the room to be canceled
     */
    public static void cancelBooking(Room room, String creditCardNumber) {
        threadPool.submit(() -> {
            if (room.getRoomStatus() == Room.RoomStatus.RESERVED) {
                room.setRoomStatus(Room.RoomStatus.AVAILABLE);
                HotelSystem.getReservedRooms().remove(room);
                HotelSystem.getAvailableRooms().add(room);
                System.out.println("Room " + room.getRoomNum() + " booking has been canceled.");

                refundPayment(room, creditCardNumber);
            } else {
                System.out.println("Room " + room.getRoomNum() + " is not currently reserved.");
            }
        });
    }
}
