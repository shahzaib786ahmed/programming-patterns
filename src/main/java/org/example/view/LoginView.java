package org.example.view;

import org.example.controller.AuthentificationController;
import org.example.controller.DatabaseController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginView extends JFrame {
    private DatabaseController databaseController;
    private Locale currentLocale; // Current locale for the UI
    private ResourceBundle bundle; // Resource bundle for translations
    private JPanel panel;

    public LoginView(Locale locale) {
        this.currentLocale = locale != null ? locale : Locale.getDefault();
        this.bundle = ResourceBundle.getBundle("messages", this.currentLocale);
        initializeUI();
    }

    /**
     * This method initializes the user interface
     */
    private void initializeUI() {
        // Frame settings
        setTitle(bundle.getString("title"));
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel
        panel = new JPanel();
        panel.setLayout(null);

        // Language Selection Combo Box
        JLabel languageLabel = new JLabel(bundle.getString("selectLanguage"));
        languageLabel.setBounds(50, 10, 100, 30);
        panel.add(languageLabel);

        JComboBox<String> languageComboBox = new JComboBox<>(new String[]{"English", "Français"});
        languageComboBox.setBounds(150, 10, 150, 30);
        panel.add(languageComboBox);

        // Set the initial selection based on the current locale
        switch (currentLocale.getLanguage()) {
            case "fr" -> languageComboBox.setSelectedItem("Français");
            default -> languageComboBox.setSelectedItem("English");
        }

        // Listener for language changes
        languageComboBox.addActionListener(e -> {
            String selectedLanguage = (String) languageComboBox.getSelectedItem();
            Locale newLocale;
            switch (selectedLanguage) {
                case "Français" -> newLocale = new Locale("fr", "FR");
                default -> newLocale = Locale.US;
            }

            // Reload the UI with the new locale
            new LoginView(newLocale).setVisible(true);
            dispose();
        });

        // User Type Selection
        JLabel selectLabel = new JLabel(bundle.getString("loginAs"));
        selectLabel.setBounds(50, 50, 100, 30);
        panel.add(selectLabel);

        JRadioButton employeeButton = new JRadioButton(bundle.getString("employee"));
        employeeButton.setBounds(50, 90, 100, 30);
        JRadioButton managerButton = new JRadioButton(bundle.getString("manager"));
        managerButton.setBounds(150, 90, 100, 30);
        JRadioButton clientButton = new JRadioButton(bundle.getString("client"));
        clientButton.setBounds(250, 90, 100, 30);

        ButtonGroup group = new ButtonGroup();
        group.add(employeeButton);
        group.add(managerButton);
        group.add(clientButton);
        panel.add(employeeButton);
        panel.add(managerButton);
        panel.add(clientButton);

        // Username and Password Fields
        JLabel userLabel = new JLabel(bundle.getString("username"));
        userLabel.setBounds(50, 130, 100, 30);
        panel.add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 130, 150, 30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel(bundle.getString("password"));
        passLabel.setBounds(50, 170, 100, 30);
        panel.add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 170, 150, 30);
        panel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton(bundle.getString("login"));
        loginButton.setBounds(50, 210, 100, 30);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (employeeButton.isSelected()) {
                    if (AuthentificationController.isLogin(username, password)) {
                        JOptionPane.showMessageDialog(null, bundle.getString("welcomeEmployee"));
                        new EmployeeView(username, currentLocale).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, bundle.getString("invalidEmployeeCredentials"));
                    }
                } else if (clientButton.isSelected()) {
                    if (AuthentificationController.isLogin(username, password)) {
                        JOptionPane.showMessageDialog(null, bundle.getString("welcomeClient"));

                        // Pass the selected Locale (e.g., dynamically retrieved from a settings menu or combo box)
                        new ClientView(username, currentLocale).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, bundle.getString("invalidClientCredentials"));
                    }

                } else if (managerButton.isSelected()) {
                    if (AuthentificationController.isLogin(username, password)) {
                        JOptionPane.showMessageDialog(null, bundle.getString("welcomeManager"));
                        new EmployeeView(username, currentLocale).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, bundle.getString("invalidManagerCredentials"));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, bundle.getString("pleaseSelectUserType"));
                }
            }
        });
        panel.add(loginButton);

        // Create Account Button
        JButton createAccountButton = new JButton(bundle.getString("createAccount"));
        createAccountButton.setBounds(200, 210, 150, 30);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientButton.isSelected()) {
                    new CreateAccountView(currentLocale).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, bundle.getString("pleaseSelectClientToCreateAccount"));
                }
            }
        });
        panel.add(createAccountButton);

        add(panel);
    }
}
