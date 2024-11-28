package org.example.controller;

import org.example.model.Client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AuthentificationController {
    private Client client;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    //TODO: FIGURE OUT IF CLIENT OBJECT IS NEEDED IN CONSTRUCTOR
    public AuthentificationController() {
        initTable();
    }

    private void initTable() {
        DatabaseController.createAccountTable();
    }

    public void insertAccount(String username, String password) {
        threadPool.submit(() -> {
            client.setUserName(username);
            client.setPassword(password);
            DatabaseController.insertAccount(username, password);
        });
    }

    public void changeUsername(String newUsername, String oldUsername, String password) {
        threadPool.submit(() -> {
            client.setUserName(newUsername);
            DatabaseController.updateUsername(newUsername, oldUsername, password);
        });
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        threadPool.submit(() -> {
            client.setPassword(newPassword);
            DatabaseController.updatePassword(username, oldPassword, newPassword);
        });
    }
}
