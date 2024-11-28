package org.example.controller;

import org.example.model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class CompanySystemController {
    private CompanySystem companySystem;
    private Review review;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public CompanySystemController() {
        this.companySystem = CompanySystem.getInstance();
        initTables();
    }

    private void initTables() {
        DatabaseController.createNewTableOfClients();
        DatabaseController.createEmployeeTable();
        DatabaseController.createManagerTable();
    }

    public void addClient(Client client) {
        threadPool.submit(() -> {
            companySystem.getClients().add(client);
            DatabaseController.insertClient(client);
        });
    }

    public void addEmployee(Employee employee) {
        threadPool.submit(() -> {
            companySystem.getEmployees().add(employee);
            DatabaseController.insertEmployee(employee);
        });
    }
    public void addManager(Manager manager) {
        threadPool.submit(() -> {
            companySystem.getManagers().add(manager);
            DatabaseController.insertManager(manager);
        });
    }
    public void deleteClient(Client client) {
        threadPool.submit(() -> {
            companySystem.getClients().remove(client);
            DatabaseController.deleteClient(client.getId());
        });
    }
    public void deleteEmployee(Employee employee) {
        threadPool.submit(() -> {
            companySystem.getEmployees().remove(employee);
            DatabaseController.deleteEmployee(employee.getId());
        });
    }
    public void deleteManager(Manager manager) {
        threadPool.submit(() -> {
            companySystem.getManagers().remove(manager);
            DatabaseController.deleteManager(manager.getId());
        });
    }

    public void displayClients() {
        threadPool.submit(() -> {
            companySystem.getClients().forEach(System.out::println);
        });
    }

    public void displayEmployee() {
        threadPool.submit(() -> {
            companySystem.getEmployees().forEach(System.out::println);
        });
    }

    public void displayManagers() {
        threadPool.submit(() -> {
            companySystem.getManagers().forEach(System.out::println);
        });
    }

    public void addReview(Review review) {
        threadPool.submit(() -> {
           DatabaseController.insertReview(review);
        });
    }

    public void deleteReview(String id) {
        threadPool.submit(() -> {
            DatabaseController.deleteReview(id);
        });
    }
}
