package org.example.controller;

import org.example.model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookingFlightController {
    private TicketSystem ticketSystem;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public BookingFlightController() {
        this.ticketSystem = TicketSystem.getInstance();
        initTables();
    }
    private void initTables(){
            DatabaseController.createFlightTable();
            DatabaseController.createTicketTable();
    }

    public void addEmployee(Employee employee){
        threadPool.submit(()->{
            TicketSystem.getEmployees().add(employee);
            DatabaseController.insertEmployee(employee);
        });
    }

    public void addClient(Client client) {
        threadPool.submit(() -> {
            TicketSystem.getClients().add(client);
            DatabaseController.insertClient(client);
        });
    }

    public void addManager(Manager manager) {
        threadPool.submit(() -> {
            TicketSystem.getManagers().add(manager);
            DatabaseController.insertManager(manager);
        });
    }

    public void addFlight(Flight flight) {
        threadPool.submit(() -> {
            DatabaseController.insertFlight(flight);
        });
    }

    public void removeFlight(int id) {
        threadPool.submit(() -> {
            DatabaseController.deleteFlight(id);
        });
    }

    public void viewAllFlights() {
        threadPool.submit(() -> {
           DatabaseController.queryAllFlight();
        });
    }

    public void addTicket(Ticket ticket) {
        threadPool.submit(() -> {
           DatabaseController.insertTicket(ticket);
           TicketSystem.getBoughtTickets().add(ticket);
        });
    }

    public void cancelTicket(Ticket ticket) {
        threadPool.submit(() -> {
            TicketSystem.getBoughtTickets().remove(ticket);
            DatabaseController.deleteTicket(ticket.getTicketId());
            TicketSystem.getCancelledTickets().add(ticket);
        });
    }

    public void viewAllTickets() {
        threadPool.submit(() -> {
           DatabaseController.queryAllTickets();
        });
    }

    public void viewAllPurchasedTickets() {
        threadPool.submit(() -> {
            DatabaseController.queryAllBoughtTickets();
        });
    }

    public void viewAllCancelledTickets() {
        threadPool.submit(() -> {
           DatabaseController.queryAllCanceledTickets();
        });
    }

}
