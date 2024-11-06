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
        return null;
    }

    public void displayAllTickets(List<Ticket> tickets) {

    }

    public void add(Client client) {

    }

    public void add(Employee employee) {

    }
}
