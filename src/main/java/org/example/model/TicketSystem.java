package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.DatabaseController;

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

    private static TicketSystem ticketSystem;

    private TicketSystem() {
        this.clients = DatabaseController.queryAllClients();
        this.employees = DatabaseController.queryAllEmployees();
        this.managers = DatabaseController.queryAllManagers();
        this.cancelledTickets = DatabaseController.queryAllCanceledTickets();
        this.boughtTickets = DatabaseController.queryAllBoughtTickets();

    }

    //TODO:PUT IN CONTROLLER THE 2 FOLLOWING
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
    /**
     * If an instance of AgencyDetails is not created, it's created
     * @return using singleton, to see if there's one instance created only, if not, create one
     */
    public static TicketSystem getInstance() {
        if (ticketSystem == null) {
            synchronized (AgencyDetails.class) {
                if (ticketSystem == null) {
                    ticketSystem = new TicketSystem();
                }
            }
        }
        return ticketSystem;
    }
}
