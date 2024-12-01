package org.example.controller;

import org.example.model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompanySystemController {
    private CompanySystem companySystem;
    private AgencyDetails agencyDetails;
    private Review review;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public CompanySystemController() {
        initTables();
        this.companySystem = CompanySystem.getInstance();
    }

    /**
     * Initializes the database tables needed for the system, including tables for clients, employees, managers, and reviews.
     */
    public void initTables() {
        System.out.println("Initializing database tables...");
        DatabaseController.createClientTable();
        DatabaseController.createEmployeeTable();
        DatabaseController.createManagerTable();
        DatabaseController.createReviewTable();
        System.out.println("All tables initialized.");
    }

    /**
     * Adds a new client to the system and the database asynchronously.
     *
     * @param client the client to be added
     */
    public void addClient(Client client) {
        threadPool.submit(() -> {
            companySystem.getClients().add(client);
            DatabaseController.insertClient(client);
        });
    }

    /**
     * Adds a new employee to the system and the database asynchronously.
     *
     * @param employee the employee to be added
     */
    public void addEmployee(Employee employee) {
        threadPool.submit(() -> {
            companySystem.getEmployees().add(employee);
            DatabaseController.insertEmployee(employee);
        });
    }

    /**
     * Adds a new manager to the system and the database asynchronously.
     *
     * @param manager the manager to be added
     */
    public void addManager(Manager manager) {
        threadPool.submit(() -> {
            companySystem.getManagers().add(manager);
            DatabaseController.insertManager(manager);
        });
    }

    /**
     * Deletes a client from the system and the database asynchronously.
     *
     * @param client the client to be deleted
     */
    public void deleteClient(Client client) {
        threadPool.submit(() -> {
            companySystem.getClients().remove(client);
            DatabaseController.deleteClient(client.getId());
        });
    }

    /**
     * Deletes an employee from the system and the database asynchronously.
     *
     * @param employee the employee to be deleted
     */
    public void deleteEmployee(Employee employee) {
        threadPool.submit(() -> {
            companySystem.getEmployees().remove(employee);
            DatabaseController.deleteEmployee(employee.getId());
        });
    }

    /**
     * Deletes a manager from the system and the database asynchronously.
     *
     * @param manager the manager to be deleted
     */
    public void deleteManager(Manager manager) {
        threadPool.submit(() -> {
            companySystem.getManagers().remove(manager);
            DatabaseController.deleteManager(manager.getId());
        });
    }

    /**
     * Displays all clients in the system asynchronously.
     */
    public void displayClients() {
        threadPool.submit(() -> {
            companySystem.getClients().forEach(System.out::println);
        });
    }

    /**
     * Displays all employees in the system asynchronously.
     */
    public void displayEmployee() {
        threadPool.submit(() -> {
            companySystem.getEmployees().forEach(System.out::println);
        });
    }

    /**
     * Displays all managers in the system asynchronously.
     */
    public void displayManagers() {
        threadPool.submit(() -> {
            companySystem.getManagers().forEach(System.out::println);
        });
    }

    /**
     * Adds a new review to the system and the database asynchronously.
     *
     * @param review the review to be added
     */
    public void addReview(Review review) {
        threadPool.submit(() -> {
            agencyDetails.getCustomerReviews().add(review);
            DatabaseController.insertReview(review);
        });
    }

    /**
     * Deletes a review from the system and the database asynchronously by its ID.
     *
     * @param id the ID of the review to be deleted
     */
    public void deleteReview(String id) {
        threadPool.submit(() -> {
            agencyDetails.getCustomerReviews().remove(id);
            DatabaseController.deleteReview(id);
        });
    }

    /**
     * Displays all reviews in the system asynchronously.
     */
    public void queryAllReviews() {
        threadPool.submit(() -> {
            agencyDetails.getCustomerReviews().forEach(System.out::println);
        });
    }
}
