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
            DatabaseController.insertAccount(client.getUsername(), client.getPassword());
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
            DatabaseController.insertAccount(employee.getUsername(), employee.getPassword());
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
            DatabaseController.insertAccount(manager.getUsername(), manager.getPassword());
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
            DatabaseController.deleteAccount(client.getId());
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
            DatabaseController.deleteAccount(employee.getId());
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
            DatabaseController.deleteAccount(manager.getId());
        });
    }

    public void updateClient(Client client, String phoneNumber, String emailAddress) {
        threadPool.submit(() -> {
            DatabaseController.updateClient(client.getId(), phoneNumber, emailAddress);
        });
    }

    public void updateEmployee(Employee employee, String phoneNumber, String emailAddress) {
        threadPool.submit(() -> {
            DatabaseController.updateEmployee(employee.getId(), phoneNumber, emailAddress);
        });
    }

    public void updateManager(Manager manager, String phoneNumber, String emailAddress) {
        threadPool.submit(() -> {
            DatabaseController.updateManager(manager.getId(), phoneNumber, emailAddress);
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
     * @param review the ID of the review to be deleted
     */
    public void deleteReview(Review review) {
        threadPool.submit(() -> {
            agencyDetails.getCustomerReviews().remove(review);
            DatabaseController.deleteReview(review.getReviewId());
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
