package org.example.model;

import java.util.List;

public class TicketSystem {
    private List<Client> clients;
    private List<Employee> employees;
    private MyQueue<Ticket> unassignedTickets;
    private List<Ticket> cancelledTickets;
    private List<Ticket> processingTickets;
    private List<Ticket> boughtTickets;

    public TicketSystem(List<Client> clients, List<Employee> employees, MyQueue<Ticket> unassignedTickets, List<Ticket> cancelledTickets, List<Ticket> processingTickets, List<Ticket> boughtTickets) {
        this.clients = clients;
        this.employees = employees;
        this.unassignedTickets = unassignedTickets;
        this.cancelledTickets = cancelledTickets;
        this.processingTickets = processingTickets;
        this.boughtTickets = boughtTickets;
    }

    public Ticket search(Ticket ticket) {
        if (boughtTickets.contains(ticket)) {
            System.out.print("Ticket: ");
            return ticket;
        } else {
            throw new IllegalArgumentException("Ticket does not exist.");
        }
    }

    public void displayAllTickets(List<Ticket> tickets) {
        if (!tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                System.out.println("--------------------------------------------------");
                System.out.println("Ticket: ");
                ticket.displayDetails();
                System.out.println("--------------------------------------------------");
            }
        }
    }

    public void add(Client client) {
        //TODO: TO BE DISCUSSED
    }

    public void add(Employee employee) {
        //TODO: TO BE DISCUSSED
    }
}
