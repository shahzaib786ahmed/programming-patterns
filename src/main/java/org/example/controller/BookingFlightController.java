package org.example.controller;

import org.example.model.Employee;
import org.example.model.TicketSystem;

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
            ticketSystem.getEmployees
        })
    }

}
