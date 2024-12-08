package org.example;

import org.example.controller.*;
import org.example.model.*;
import org.example.view.WelcomeView;

import javax.swing.*;
import java.util.UUID;

public class Main {
    //TODO: IF WE RUN DIRECTLY CSM IT DOESNT FIND THE TABLES
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomeView().setVisible(true)); // Open the WelcomeView
    }}