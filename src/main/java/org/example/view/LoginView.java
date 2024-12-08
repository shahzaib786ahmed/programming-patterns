package org.example.view;

import org.example.controller.AuthentificationController;
import org.example.controller.DatabaseController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private DatabaseController databaseController;

    public LoginView(){
        // Frame settings
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // User Type Selection
        JLabel selectLabel = new JLabel("Login as:");
        selectLabel.setBounds(50, 30, 100, 30);
        panel.add(selectLabel);

        JRadioButton employeeButton = new JRadioButton("Employee");
        employeeButton.setBounds(50, 70, 100, 30);
        JRadioButton managerButton = new JRadioButton("Manager");
        managerButton.setBounds(150, 70, 100, 30);
        JRadioButton clientButton = new JRadioButton("Client");
        clientButton.setBounds(250, 70, 100, 30); // Adjusted position

        ButtonGroup group = new ButtonGroup();
        group.add(employeeButton);
        group.add(managerButton);
        group.add(clientButton);
        panel.add(employeeButton);
        panel.add(managerButton);
        panel.add(clientButton);

        // Username and Password Fields
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 110, 100, 30);
        panel.add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 110, 150, 30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 150, 100, 30);
        panel.add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 150, 30);
        panel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 200, 100, 30);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (employeeButton.isSelected()) {
                    if (AuthentificationController.isLogin(username, password)) {
                        JOptionPane.showMessageDialog(null, "Welcome Employee!");
                        new EmployeeView(username).setVisible(true); // Open Employee Form
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Employee Credentials.");
                    }
                } else if (clientButton.isSelected()) {
                    if (AuthentificationController.isLogin(username, password)) {
                        JOptionPane.showMessageDialog(null, "Welcome Client!");
                        new ClientView(username).setVisible(true); // Open Client Form
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Client Credentials.");
                    }
                } else if (managerButton.isSelected()) {
                    if (AuthentificationController.isLogin(username, password)) {
                        JOptionPane.showMessageDialog(null, "Welcome Manager!");
                        new ManagerView().setVisible(true); // Open Manager Form
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Manager Credentials.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a user type.");
                }
            }
        });
        panel.add(loginButton);

        // Create Account Button
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(200, 200, 150, 30);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientButton.isSelected()) {
                    new CreateAccountView().setVisible(true); // Open Create Account Form
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select 'Client' to create an account.");
                }
            }
        });
        panel.add(createAccountButton);

        add(panel);
    }
}