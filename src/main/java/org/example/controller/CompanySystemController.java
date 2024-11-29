package org.example.controller;

import org.example.model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class CompanySystemController {
    private CompanySystem companySystem;
    private AgencyDetails agencyDetails;
    private Review review;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public CompanySystemController() {
        this.companySystem = CompanySystem.getInstance();
        initTables();
    }

    /**
     * Initializes the database tables needed for the system, including tables for clients, employees, managers, and reviews.
     */
    private void initTables() {
        DatabaseController.createNewTableOfClients();
        DatabaseController.createEmployeeTable();
        DatabaseController.createManagerTable();
        DatabaseController.createReviewTable();
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
            DatabaseController.insertReview(review);
            agencyDetails.getCustomerReviews().add(review);
        });
    }

    /**
     * Deletes a review from the system and the database asynchronously by its ID.
     *
     * @param id the ID of the review to be deleted
     */
    public void deleteReview(String id) {
        threadPool.submit(() -> {
            DatabaseController.deleteReview(id);
            agencyDetails.getCustomerReviews().remove(review.getReviewId());
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
