package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public static MyQueue<Ticket> unassignedTickets = new MyQueue<>();//linkedlist doesnt work
    @Getter
    public static List<Ticket> cancelledTickets = new ArrayList<>();
    @Getter
    public static List<Ticket> processingTickets = new ArrayList<>();
    @Getter
    public static List<Ticket> boughtTickets = new ArrayList<>();

    public TicketSystem(List<Client> clients, List<Employee> employees, MyQueue<Ticket> unassignedTickets, List<Ticket> cancelledTickets, List<Ticket> processingTickets, List<Ticket> boughtTickets) {
        this.clients = clients;
        this.employees = employees;
        this.unassignedTickets = unassignedTickets;
        this.cancelledTickets = cancelledTickets;
        this.processingTickets = processingTickets;
        this.boughtTickets = boughtTickets;
    }

    public static Ticket search(int keyword) {
        for (Ticket ticket : unassignedTickets) {
            if (ticket.getTicketId() == keyword) {
                return ticket;
            }
        }
        for (Ticket ticket : processingTickets) {
            if (ticket.getTicketId() == keyword) {
                return ticket;
            }
        }
        for (Ticket ticket : cancelledTickets) {
            if (ticket.getTicketId() == keyword) {
                return ticket;
            }
        }
        for (Ticket ticket : boughtTickets) {
            if (ticket.getTicketId() == keyword) {
                return ticket;
            }
        }
        return null;
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

    public static void add(Client client) {
        clients.add(client);
    }

    public static void add(Employee employee) {
        employees.add(employee);
    }

    public static void add(Manager manager) {
        employees.add(manager);
        managers.add(manager);
    }
}
