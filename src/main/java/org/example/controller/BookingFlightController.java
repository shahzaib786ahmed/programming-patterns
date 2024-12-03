package org.example.controller;

import org.example.model.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookingFlightController {
    private TicketSystem ticketSystem;
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(100);

    public BookingFlightController() {
        initTables();
        this.ticketSystem = TicketSystem.getInstance();
    }

    /**
     * Initializes the flight and ticket tables in the database.
     */
    private void initTables() {
        DatabaseController.createFlightTable();
        DatabaseController.createTicketTable();
    }

    /**
     * Adds an employee to the system and database.
     *
     * @param employee the employee to be added
     */
    public void addEmployee(Employee employee) {
        threadPool.submit(() -> {
            TicketSystem.getEmployees().add(employee);
            DatabaseController.insertEmployee(employee);
        });
    }

    /**
     * Adds a client to the system and database.
     *
     * @param client the client to be added
     */
    public void addClient(Client client) {
        threadPool.submit(() -> {
            TicketSystem.getClients().add(client);
            DatabaseController.insertClient(client);
        });
    }

    /**
     * Adds a manager to the system and database.
     *
     * @param manager the manager to be added
     */
    public void addManager(Manager manager) {
        threadPool.submit(() -> {
            TicketSystem.getManagers().add(manager);
            DatabaseController.insertManager(manager);
        });
    }

    /**
     * Adds a flight to the system and database.
     *
     * @param flight the flight to be added
     */
    public void addFlight(Flight flight) {
        threadPool.submit(() -> {
            DatabaseController.insertFlight(flight);
        });
    }

    /**
     * Removes a flight from the system and database by its ID.
     *
     * @param flightNum the ID of the flight to be removed
     */
    public void removeFlight(String flightNum) {
        threadPool.submit(() -> {
            DatabaseController.deleteFlight(flightNum);
        });
    }

    /**
     * Views all flights in the system by querying the database.
     */
    public void viewAllFlights() {
        threadPool.submit(() -> {
            DatabaseController.queryAllFlight();
        });
    }

    /**
     * Adds a ticket to the system and database.
     *
     * @param ticket the ticket to be added
     */
    public void addTicket(Ticket ticket) {
        threadPool.submit(() -> {
            DatabaseController.insertTicket(ticket);
            TicketSystem.getBoughtTickets().add(ticket);
        });
    }

    /**
     * Views all tickets in the system by querying the database.
     */
    public void viewAllTickets() {
        threadPool.submit(() -> {
            DatabaseController.queryAllTickets();
        });
    }

    /**
     * Views all purchased tickets in the system by querying the database.
     */
    public void viewAllPurchasedTickets() {
        threadPool.submit(() -> {
            DatabaseController.queryAllBoughtTickets();
        });
    }

    /**
     * Views all cancelled tickets in the system by querying the database.
     */
    public void viewAllCancelledTickets() {
        threadPool.submit(() -> {
            DatabaseController.queryAllCanceledTickets();
        });
    }

    /**
     * Searches for a specific ticket using its ticket ID.
     *
     * @param ticket_id the ticket ID to search for
     */
    public void search(int ticket_id) {
        threadPool.submit(() -> {
            DatabaseController.querySpecificTicket(ticket_id);
        });
    }

    /**
     * Makes a payment for a flight ticket using either credit or loyalty points.
     *
     * @param ticket           the ticket to be paid for
     * @param paymentType      the payment type ("CREDIT" or "LOYALTY")
     * @param recipientEmail   the email address to send payment confirmation
     * @param creditCardNumber the credit card number for payment (if applicable)
     */
    private void makePayment(Ticket ticket, String paymentType, String recipientEmail, String creditCardNumber) {
        threadPool.submit(() -> {
            double flightPrice = ticket.getFlight().getPrice();
            double serviceFee = 100.00;
            double totalCost = flightPrice + serviceFee;

            if (ticket == null) {
                throw new IllegalArgumentException("Ticket cannot be null.");
            }
            if ("CREDIT".equalsIgnoreCase(paymentType)) {
                if (!creditCardNumber.matches("\\d{16}")) {
                    System.out.println("Invalid credit card number format. Payment failed.");
                    return;
                }
                System.out.println("Payment of $" + totalCost + " made successfully via credit card ending with " + creditCardNumber.substring(creditCardNumber.length() - 4));

                if (ticket.getClient() != null) {
                    TicketSystem.paymentHistory.add(ticket.getClient().getLName() + ", " + ticket.getClient().getFName() + " | Amount Paid: " + totalCost + " | Payment type: " + paymentType + " | Credit card used: " + creditCardNumber);
                } else {
                    TicketSystem.paymentHistory.add("Employee Ticket | Amount Paid: " + totalCost + " | Payment type: " + paymentType + " | Credit card used: " + creditCardNumber);
                }
            } else if ("LOYALTY".equalsIgnoreCase(paymentType)) {
                Client client = ticket.getClient();
                int loyaltyPoints = client.getLoyaltyPoints();
                if (loyaltyPoints < totalCost) {
                    System.out.println("Insufficient loyalty points. Credit Card used for purchase.");
                    return;
                }

                int pointsLeft = loyaltyPoints - (int) totalCost;
                client.setLoyaltyPoints(pointsLeft);
                System.out.println("Payment of $" + totalCost + " made successfully with loyalty points.");
                TicketSystem.paymentHistory.add(client.getLName() + ", " + client.getFName() + " | Payment type: " + paymentType + " | Points remaining: " + pointsLeft);
            } else {
                System.out.println("Invalid payment type.");
                return;
            }

            sendConfirmationEmail(recipientEmail, "Payment Confirmation", "Your payment of $" + totalCost + " was successful for ticket " + ticket.getTicketId());
        });
    }

    /**
     * Sends a payment confirmation email to the recipient.
     *
     * @param recipientEmail the recipient's email address
     * @param subject        the subject of the email
     * @param messageBody    the body of the email
     */
    private void sendConfirmationEmail(String recipientEmail, String subject, String messageBody) {
        threadPool.submit(() -> {
            System.out.println("Sending email to: " + recipientEmail);
            System.out.println("Subject: " + subject);
            System.out.println("Message: " + messageBody);
            System.out.println("Email sent successfully.");
        });
    }

    /**
     * Validates the flight seat availability and reserves a seat.
     *
     * @param flight the flight to check for seat availability
     */
    private void validateAndReserveSeat(Flight flight) {
        if (flight.getFlightSeatNumber() == 0) {
            throw new IllegalArgumentException("Flight is fully booked!");
        }
        flight.setFlightSeatNumber(flight.getFlightSeatNumber() - 1);
    }

    /**
     * Purchases a two-way (round-trip) flight ticket for the client
     *
     * @param flight           to be booked for the passenger
     * @param client           that is purchasing the ticket
     * @param seatNumber       of the passenger where they will be assigned
     * @param departureDate    of the flight selected
     * @param returnDate       of the flight selected
     * @param paymentType      to make the payment for the ticket
     * @param recipientEmail   of the passenger to send email of confirmation
     * @param creditCardNumber of the passenger to be used if paid by credit card
     */
    public Ticket purchaseFlightTicket(Flight flight, Client client, String seatNumber, String departureDate, String returnDate, String paymentType, String recipientEmail, String creditCardNumber) {
        Ticket ticket = new Ticket(flight, client, seatNumber, departureDate, returnDate, paymentType);
        threadPool.submit(() -> {
            validateAndReserveSeat(flight);

            makePayment(ticket, paymentType, recipientEmail, creditCardNumber);
            addTicket(ticket);

            ticket.setTicketStatus(Status.PURCHASED);
            client.setLoyaltyPoints((int) flight.getPrice() / 4);

            System.out.println("Ticket has been purchased for client: " + client.getLName() + ", " + client.getFName() +
                    " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
            System.out.println("Here's your ticket: ");
            //do we use search ticket bc it displays a specific ticket but now that method needs to be verified by yi

            //Search ticket is for if a customer or employee want to look up a specific ticket for any client and displayDetails is good for
            //printing after purchase like an overall look of the purchased ticket, so I think we should keep it, but we can discuss with yi if you still want
            ticket.displayDetails();
        });

        return ticket;
    }

    /**
     * Purchases a two-way (round-trip) flight ticket for the employee
     *
     * @param flight           to be booked for the passenger
     * @param employee         that is booking the ticket
     * @param seatNumber       of the passenger where they will be assigned
     * @param departureDate    of the flight selected
     * @param returnDate       of the flight selected
     * @param paymentType      to make the payment for the ticket
     * @param recipientEmail   of the passenger to send email of confirmation
     * @param creditCardNumber of the passenger to be used to make the payment
     * @return a new ticket for the passenger
     */
    public Ticket purchaseEmployeeFlightTicket(Flight flight, Employee employee, String seatNumber, String departureDate, String returnDate, String paymentType, String recipientEmail, String creditCardNumber) {
        Ticket ticket = new Ticket(flight, seatNumber, departureDate, returnDate, paymentType);
        threadPool.submit(() -> {
            validateAndReserveSeat(flight);

            flight.setPrice(flight.getPrice() - (flight.getPrice() * (employee.getDiscountRate() / 100)));
            makePayment(ticket, paymentType, recipientEmail, creditCardNumber);
            addTicket(ticket);

            ticket.setTicketStatus(Status.PURCHASED);

            System.out.println("Ticket has been purchased for employee: " + employee.getLName() + ", " + employee.getFName() +
                    " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
            System.out.println("Here's your ticket: ");
            //do we use search ticket bc it displays a specific ticket but now that method needs to be verified by yi

            //Search ticket is for if a customer or employee want to look up a specific ticket for any client and displayDetails is good for
            //printing after purchase like an overall look of the purchased ticket, so I think we should keep it, but we can discuss with yi if you still want
            ticket.displayDetails();
        });

        return ticket;
    }

    /**
     * Purchases a one-way flight ticket for the client
     *
     * @param flight           to be booked for the passenger
     * @param client           that is purchasing the ticket
     * @param seatNumber       of the passenger where they will be assigned
     * @param departureDate    of the flight selected
     * @param paymentType      to make the payment for the ticket
     * @param recipientEmail   of the passenger to send email of confirmation
     * @param creditCardNumber of the passenger to be used if paid by credit card
     * @return a new ticket for the passenger
     */
    public Ticket purchaseOneWayFlightTicket(Flight flight, Client client, String seatNumber, String departureDate, String paymentType, String recipientEmail, String creditCardNumber) {
        Ticket ticket = new Ticket(flight, client, seatNumber, departureDate, paymentType);
        threadPool.submit(() -> {
            validateAndReserveSeat(flight);

            makePayment(ticket, paymentType, recipientEmail, creditCardNumber);
            addTicket(ticket);
            ticket.setTicketStatus(Status.PURCHASED);
            client.setLoyaltyPoints((int) flight.getPrice() / 4);

            System.out.println("Ticket has been purchased for client: " + client.getLName() + ", " + client.getFName() +
                    " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
            System.out.println("Here's your ticket: ");
            //do we use search ticket bc it displays a specific ticket but now that method needs to be verified by yi

            //Search ticket is for if a customer or employee want to look up a specific ticket for any client and displayDetails is good for
            //printing after purchase like an overall look of the purchased ticket, so I think we should keep it, but we can discuss with yi if you still want
            ticket.displayDetails();

        });

        return ticket;
    }

    /**
     * Purchases a one-way flight ticket for the employee
     *
     * @param flight           to be booked for the passenger
     * @param employee         that is booking the ticket
     * @param seatNumber       of the passenger where they will be assigned
     * @param departureDate    of the flight selected
     * @param paymentType      to make the payment for the ticket
     * @param recipientEmail   of the passenger to send email of confirmation
     * @param creditCardNumber of the passenger to be used to make the payment
     * @return a new ticket for the passenger
     */
    public Ticket purchaseEmployeeOneWayFlightTicket(Flight flight, Employee employee, String seatNumber, String departureDate, String paymentType, String recipientEmail, String creditCardNumber) {

        Ticket ticket = new Ticket(flight, seatNumber, departureDate, paymentType);
        threadPool.submit(() -> {
            validateAndReserveSeat(flight);

            flight.setPrice(flight.getPrice() - (flight.getPrice() * (employee.getDiscountRate() / 100)));
            ticket.setTicketStatus(Status.PURCHASED);
            addTicket(ticket);
            makePayment(ticket, paymentType, recipientEmail, creditCardNumber);

            System.out.println("Ticket has been purchased for employee: " + employee.getLName() + ", " + employee.getFName() +
                    " for flight: " + flight.getFlightNumber() + " from: " + flight.getDepartureLocation() + " to: " + flight.getArrivalLocation());
            System.out.println("Here's your ticket: ");
            //do we use search ticket bc it displays a specific ticket but now that method needs to be verified by yi

            //Search ticket is for if a customer or employee want to look up a specific ticket for any client and displayDetails is good for
            //printing after purchase like an overall look of the purchased ticket, so I think we should keep it, but we can discuss with yi if you still want
            ticket.displayDetails();

        });

        return ticket;
    }

    /**
     * Refunds a ticket to the passenger
     *
     * @param ticket of the passenger that wants/needs a refund
     */
    private void refund(Ticket ticket) {
        threadPool.submit(() -> {
            Client client = ticket.getClient();
            String paymentType = ticket.getPaymentType();
            if (client == null) {
                System.out.println("Client cannot be null.");
                return;
            }
            if (ticket == null) {
                System.out.println("Ticket cannot be null.");
                return;
            }
            if (!ticket.getClient().equals(client)) {
                System.out.println("Ticket does not belong to the client.");
                return;
            }
            if ("CREDIT".equalsIgnoreCase(paymentType)) {
                System.out.println("Refund processed to credit card for ticket " + ticket.getTicketId());
                if (ticket.getClient() != null) {
                    TicketSystem.paymentHistory.add("Ticket refunded for: " + ticket.getClient().getLName() + ", " + ticket.getClient().getFName() + " with Ticket ID: " + ticket.getTicketId() + " using: " + ticket.getPaymentType());
                } else {
                    TicketSystem.paymentHistory.add("Amount refunded to employee with Ticket ID: " + ticket.getTicketId());
                }
            } else if ("LOYALTY".equalsIgnoreCase(paymentType)) {
                client.setLoyaltyPoints(client.getLoyaltyPoints() + (int) ticket.getFlight().getPrice());
                System.out.println("Refund processed using loyalty points for ticket " + ticket.getTicketId());
            }

            sendConfirmationEmail(client.getEmailAddress(), "Cancellation Confirmation", "Your ticket " + ticket.getTicketId() + " has been cancelled and a refund has been processed.");
        });
    }

    /**
     * Cancels a ticket and removes it from the system and database.
     *
     * @param ticket the ticket to be cancelled
     */
    private void cancelTicket(Ticket ticket) {
        threadPool.submit(() -> {
            TicketSystem.getBoughtTickets().remove(ticket);
            //DatabaseController.deleteTicket(ticket.getTicketId());
            TicketSystem.getCancelledTickets().add(ticket);
        });
    }

    /**
     * Cancels a flight ticket for the passenger
     *
     * @param ticket of the passenger that wants/needs to cancel their flight
     */
    public void cancelFlightTicket(Ticket ticket) {
        threadPool.submit(() -> {
            ticket.setTicketStatus(Status.CANCELLED);
            DatabaseController.updateTicketStatus(ticket.getTicketStatus(), ticket.getTicketId());
            cancelTicket(ticket);
            ticket.getFlight().setFlightSeatNumber(ticket.getFlight().getFlightSeatNumber() + 1);
            refund(ticket);
            //do we use search ticket bc it displays a specific ticket but now that method needs to be verified by yi

            //Search ticket is for if a customer or employee want to look up a specific ticket for any client and displayDetails is good for
            //printing after purchase like an overall look of the purchased ticket, so I think we should keep it, but we can discuss with yi if you still want
            System.out.println("Ticket: " + ticket.getTicketId() + " has been cancelled.");
        });
    }

    /**
     * Checks a ticket from the list of bought ticket(s) of a client
     *
     * @param ticket_id list of the client
     */
    public void viewTicketDetails(int ticket_id) {
        threadPool.submit(() -> {
            DatabaseController.querySpecificTicket(ticket_id);
        });
    }
}
