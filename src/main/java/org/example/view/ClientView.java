package org.example.view;

import org.example.controller.DatabaseController;
import org.example.model.Ticket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClientView extends JFrame {
    private String username;

    public ClientView(String username, Locale locale) {
        this.username = username;

        // Load resource bundle for localization
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        // Frame settings
        setTitle(bundle.getString("clientDashboard"));
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Welcome Label
        JLabel welcomeLabel = new JLabel(MessageFormat.format(bundle.getString("welcome"), username));
        welcomeLabel.setBounds(50, 30, 300, 30);
        panel.add(welcomeLabel);

        // ID Input
        JLabel idLabel = new JLabel(bundle.getString("enterId"));
        idLabel.setBounds(50, 80, 150, 30);
        panel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(200, 80, 150, 30);
        panel.add(idField);

        // View Tickets Button
        JButton viewTicketsButton = new JButton(bundle.getString("viewTickets"));
        viewTicketsButton.setBounds(50, 180, 150, 30);
        viewTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int clientId = Integer.parseInt(idField.getText());

                List<Ticket> tickets = DatabaseController.queryAllBoughtTicketsClient(clientId);

                if (tickets.isEmpty()) {
                    JOptionPane.showMessageDialog(null, bundle.getString("noTicketsFound"));
                } else {
                    StringBuilder ticketsInfo = new StringBuilder(bundle.getString("yourTickets") + "\n");
                    for (Ticket ticket : tickets) {
                        ticketsInfo.append(ticket.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, ticketsInfo.toString());
                }
            }
        });
        panel.add(viewTicketsButton);

        // Add Review Button
        JButton addReviewButton = new JButton(bundle.getString("addReview"));
        addReviewButton.setBounds(50, 230, 150, 30);
        addReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = JOptionPane.showInputDialog(bundle.getString("enterEmail"));
                String title = JOptionPane.showInputDialog(bundle.getString("enterTitle"));
                String body = JOptionPane.showInputDialog(bundle.getString("enterBody"));

                if (email != null && title != null && body != null && !email.isEmpty() && !title.isEmpty() && !body.isEmpty()) {
                    if (DatabaseController.insertReview(email, title, body)) {
                        JOptionPane.showMessageDialog(null, bundle.getString("reviewSuccess"));
                    } else {
                        JOptionPane.showMessageDialog(null, bundle.getString("reviewFailed"));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, bundle.getString("allFieldsRequired"));
                }
            }
        });
        panel.add(addReviewButton);

        // View Reviews Button
        JButton viewReviewsButton = new JButton(bundle.getString("viewReviews"));
        viewReviewsButton.setBounds(50, 280, 150, 30);
        viewReviewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reviews = DatabaseController.queryAllReviews().toString();
                JOptionPane.showMessageDialog(null, bundle.getString("allReviews") + "\n" + reviews);
            }
        });
        panel.add(viewReviewsButton);

        // Logout Button
        JButton logoutButton = new JButton(bundle.getString("logOut"));
        logoutButton.setBounds(50, 330, 150, 30);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomeView().setVisible(true);
                dispose();
            }
        });
        panel.add(logoutButton);

        add(panel);
        setVisible(true);
    }
}
