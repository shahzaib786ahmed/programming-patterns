package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Client;
import org.example.model.Ticket;

import java.util.ArrayList;
import java.util.List;

@Setter
public class TicketSystem {
    @Getter
    public static List<Client> clients = new ArrayList<>();
    @Getter
    public static List<Employee> employees = new ArrayList<>();
    @Getter
    public static List<Manager> managers = new ArrayList<>();
    @Getter
    public static List<Ticket> cancelledTickets = new ArrayList<>();
    @Getter
    public static List<Ticket> boughtTickets = new ArrayList<>();

    /**
     * Searches for a specific ticket using ticketID to find a ticket
     * @param keyword the ticketID to be used to search for the ticket
     * @return the ticket if found
     */
    public static Ticket search(int keyword) {
        for (Ticket ticket : cancelledTickets) {
            if (ticket.getTicketId() == keyword) {
                return ticket;
            } else {
                System.out.println("Ticket not found");
            }
        }
        for (Ticket ticket : boughtTickets) {
            if (ticket.getTicketId() == keyword) {
                return ticket;
            } else {
                System.out.println("Ticket not found");
            }
        }
        return null;
    }

    /**
     * Display all the tickets from the agency based on the input of list tickets (bought, cancelled)
     * @param tickets that will be displayed
     */
    public static void displayAllTickets(List<Ticket> tickets) {
        if (!tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                System.out.println("--------------------------------------------------");
                System.out.println("Ticket: ");
                ticket.displayDetails();
                System.out.println("--------------------------------------------------");
            }
        }
    }
}
