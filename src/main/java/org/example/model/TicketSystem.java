package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Setter
public class TicketSystem {
    @Getter
    public static List<Client> clients = new ArrayList<>();
    @Getter
    public static List<Employee> employees = new ArrayList<>();
    @Getter
    public static List<Manager> managers = new ArrayList<>();
//    @Getter
//    public static Queue<Ticket> unassignedTickets = new LinkedList<>();
    @Getter
    public static List<Ticket> cancelledTickets = new ArrayList<>();
//    maybe we dont need processing..
    @Getter
    public static List<Ticket> processingTickets = new ArrayList<>();
    @Getter
    public static List<Ticket> boughtTickets = new ArrayList<>();

    public TicketSystem(List<Client> clients, List<Employee> employees, List<Ticket> cancelledTickets, List<Ticket> processingTickets, List<Ticket> boughtTickets) {
        this.clients = clients;
        this.employees = employees;
//        this.unassignedTickets = unassignedTickets;
        this.cancelledTickets = cancelledTickets;
        this.processingTickets = processingTickets;
        this.boughtTickets = boughtTickets;
    }

    public static Ticket search(int keyword) {
//        for (Ticket ticket : unassignedTickets) {
//            if (ticket.getTicketId() == keyword) {
//                return ticket;
//            }
//        }
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
//do we need these and the method in manager ask yi
    public static void addClient(Client client) {
        clients.add(client);
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static void addManager(Manager manager) {
        employees.add(manager);
        managers.add(manager);
    }
}
