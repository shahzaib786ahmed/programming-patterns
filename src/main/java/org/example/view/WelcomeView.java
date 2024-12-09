package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class WelcomeView extends JFrame {

    /**
     * Constructs a WelcomeView object.
     * Sets up the frame, displays a welcome message, and provides a "Get Started" button that
     * leads to the LoginView when clicked.
     * The LoginView is displayed with the default system locale (Locale.US is used in this example).
     */
    public WelcomeView() {
        // Frame settings
        setTitle("Welcome to Travel Agency");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel and Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to Our Travel Agency!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(welcomeLabel, BorderLayout.CENTER);

        // Get Started Button
        JButton getStartedButton = new JButton("Get Started");
        getStartedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Defaulting to the system locale or explicitly pass Locale.ENGLISH if needed
                new LoginView(Locale.US).setVisible(true); // Open Login Form with locale support
                dispose(); // Close Welcome Form
            }
        });
        panel.add(getStartedButton, BorderLayout.SOUTH);
        add(panel);
    }
}
