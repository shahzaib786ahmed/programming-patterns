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

    @Getter
    public static MyQueue<String> paymentHistory = new MyQueue<>();

    private static TicketSystem ticketSystem;

    /**
     * Private constructor for initializing the TicketSystem instance.
     * This constructor fetches and populates the lists of clients, employees, managers, cancelled tickets, and bought tickets
     * by querying the database through the {@link DatabaseController}.
     * It is used for setting up the system's data when the TicketSystem instance is created.
     */
    private TicketSystem() {
        this.clients = DatabaseController.queryAllClients();
        this.employees = DatabaseController.queryAllEmployees();
        this.managers = DatabaseController.queryAllManagers();
        this.cancelledTickets = DatabaseController.queryAllCanceledTickets();
        this.boughtTickets = DatabaseController.queryAllBoughtTickets();
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
