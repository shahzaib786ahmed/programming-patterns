package org.example.view;

import org.example.controller.CompanySystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class EmployeeView extends JFrame {
    public EmployeeView(String employeeName, Locale currentLocale) {
        // Load the appropriate resource bundle based on the selected locale
        ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);

        // Set up the frame
        setTitle(bundle.getString("employeeDashboardTitle"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400); // Adjusted size for better visual balance
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20)); // Adjusted gap between components

        // Create a welcome label with better styling
        JLabel welcomeLabel = new JLabel(
                String.format(bundle.getString("employeeWelcomeMessage"), employeeName),
                SwingConstants.CENTER
        );
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(50, 150, 250));

        // Create buttons with localized text
        JButton openBookingButton = createStyledButton(bundle.getString("bookingFlightsButton"));
        JButton openHotelButton = createStyledButton(bundle.getString("bookingHotelsButton"));
        JButton openCompanySystemButton = createStyledButton(bundle.getString("openCompanySystemButton")); // Localized button text

        // Create a panel for the buttons with BoxLayout for vertical alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Vertical layout
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the buttons

        // Add spacing between buttons
        buttonPanel.add(openBookingButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between buttons
        buttonPanel.add(openHotelButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between buttons
        buttonPanel.add(openCompanySystemButton);

        // Add action listeners to buttons
        openBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookingFlightView(currentLocale).setVisible(true);
                dispose(); // Close the EmployeeView window
            }
        });

        openHotelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookingHotelView(currentLocale).setVisible(true);
                dispose(); // Close the EmployeeView window
            }
        });

        openCompanySystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CompanySystemView(new CompanySystemController(), currentLocale).setVisible(true); // Open CompanySystemView
                dispose(); // Close the EmployeeView window
            }
        });

        // Add components to the frame
        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    // Helper method to create buttons with consistent styling
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(50, 150, 250));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 40)); // Consistent button size
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor on hover

        // Add hover effect for a better user experience
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 180, 250)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 150, 250)); // Original blue
            }
        });

        return button;
    }
}
