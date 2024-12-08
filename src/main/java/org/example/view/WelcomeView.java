package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class WelcomeView extends JFrame {
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
