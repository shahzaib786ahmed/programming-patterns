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

    /**
     * Constructs an instance of the AuthenticationController with the given CompanySystem.
     * Initializes necessary tables for account management.
     *
     * @param companySystem the CompanySystem instance used to manage clients and other users
     */
    public AuthentificationController(CompanySystem companySystem) {
        this.companySystem = companySystem;
        initTable();
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
    public void insertAccount(String username, String password) {
        threadPool.submit(() -> {
            DatabaseController.insertAccount(username, password);  // Store in database
        });
    }

    /**
     * Changes the username of a client asynchronously. It searches for the client by
     * their old username and checks if the provided password matches the current password.
     * If valid, the username is updated in both memory and database.
     *
     * @param newUsername the new username to be set
     * @param oldUsername the current username to search for
     * @param password the current password to validate
     */
    public void changeUsername(String newUsername, String oldUsername, String password) {
        threadPool.submit(() -> {
            // Find client by old username
            Client client = null;
            for (Client c : companySystem.getClients()) {
                if (c.getUsername().equals(oldUsername)) {
                    client = c;
                    break;
                }
            }

            if (client != null && client.getPassword().equals(password)) {
                // Update the username in memory and database
                client.setUsername(newUsername);
                DatabaseController.updateUsername(newUsername, oldUsername, password);  // Update in DB
            } else {
                System.out.println("Invalid username or password");
            }
        });
    }

    /**
     * Changes the password of a client asynchronously. It searches for the client by
     * their username and checks if the provided old password matches the current password.
     * If valid, the password is updated in both memory and database.
     *
     * @param username the username of the client whose password needs to be changed
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
     * Checks if the provided login credentials are valid. This method synchronously checks
     * through the list of clients to find a match for the username and password.
     *
     * @param userName the username to check
     * @param password the password to check
     * @return true if the credentials are valid, false otherwise
     */
    public boolean isLogin(String userName, String password) {
        if (userName == null || password == null) {
            return false;
        }

        // Synchronously check client credentials
        for (Client client : companySystem.getClients()) {
            if (client.getUsername().equals(userName) && client.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
