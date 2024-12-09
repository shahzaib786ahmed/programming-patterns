package org.example;

import org.example.controller.*;
import org.example.model.*;
import org.example.view.WelcomeView;

import javax.swing.*;
import java.util.UUID;

public class Main {
 public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomeView().setVisible(true)); // Open the WelcomeView
    }}