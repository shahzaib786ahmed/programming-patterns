package org.example.controller;

import org.example.model.Client;
import org.example.model.CompanySystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Controller class responsible for authentication and account management operations.
 * It includes methods to insert accounts, change usernames, change passwords,
 * and verify login credentials.
 */
public class AuthentificationController {
    private CompanySystem companySystem;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private static DatabaseController databaseController;

    /**
     * Constructs an instance of the AuthenticationController with the given CompanySystem.
     * Initializes necessary tables for account management.
     */
    public AuthentificationController() {
        initTable();
        this.companySystem = CompanySystem.getInstance();
    }

    /**
     * Initializes the account table in the database.
     */
    private void initTable() {
        DatabaseController.createAccountTable();  // Initialize the account table
    }

    /**
     * Inserts a new account into the database asynchronously.
     *
     * @param username the username of the new account
     * @param password the password of the new account
     */
    public static void insertAccount(String username, String password) {
        threadPool.submit(() -> {
            DatabaseController.insertAccount(username, password);  // Store in database
        });
    }

    /**
     * Changes the password of a client asynchronously. It searches for the client by
     * their username and checks if the provided old password matches the current password.
     * If valid, the password is updated in both memory and database.
     *
     * @param username    the username of the client whose password needs to be changed
     * @param oldPassword the current password to validate
     * @param newPassword the new password to be set
     */
    public void changePassword(String username, String oldPassword, String newPassword) {
        threadPool.submit(() -> {
            // Find client by username
            Client client = null;
            for (Client c : companySystem.getClients()) {
                if (c.getUsername().equals(username)) {
                    client = c;
                    break;
                }
            }

            if (client != null && client.getPassword().equals(oldPassword)) {
                // Update the password in memory and database
                client.setPassword(newPassword);
                DatabaseController.updatePassword(username, oldPassword, newPassword);  // Update password in DB
            } else {
                System.out.println("Invalid username or password");
            }
        });
    }

    /**
     * Checks if the provided login credentials are valid. This method checks
     * the database to determine the user type (Client, Employee, or Manager)
     * and validates the credentials accordingly.
     *
     * @param userName the username to check
     * @param password the password to check
     * @return true if the credentials are valid, false otherwise
     */
    public static boolean isLogin(String userName, String password) {
        if (userName == null || password == null) {
            return false;
        }

        // Check if the user is a Client

        if (databaseController.checkClientAccount(userName, password)) {
            System.out.println("Login successful as Client.");
            return true;
        }

        // Check if the user is an Employee
        if (databaseController.checkEmployeeAccount(userName, password)) {
            System.out.println("Login successful as Employee.");
            return true;
        }

        // Check if the user is a Manager
        if (databaseController.checkManagerAccount(userName, password)) {
            System.out.println("Login successful as Manager.");
            return true;
        }

        // If none of the checks pass, return false
        System.out.println("Login failed: Invalid credentials.");
        return false;
    }
}
