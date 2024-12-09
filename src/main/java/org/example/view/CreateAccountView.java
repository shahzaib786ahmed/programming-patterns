package org.example.view;

import org.example.controller.AuthentificationController;
import org.example.controller.DatabaseController;
import org.example.model.Client;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * A view class for creating a new user account. This class provides a graphical interface
 * where users can input their details such as first name, last name, passport number,
 * phone number, email, age, username, and password. Upon submitting the form, the
 * account details are validated and saved to the database.
 */
public class CreateAccountView extends JFrame {
    private ResourceBundle messages;

    /**
     * Constructs the CreateAccountView with the specified locale for internationalization.
     *
     * @param locale The locale to use for displaying the interface text in the appropriate language.
     */
    public CreateAccountView(Locale locale) {
        messages = ResourceBundle.getBundle("messages", locale);

        setTitle(messages.getString("createAccount"));
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Adjusted coordinates to prevent overlapping
        JLabel firstNameLabel = new JLabel(messages.getString("firstName"));
        firstNameLabel.setBounds(50, 20, 100, 30);
        panel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(150, 20, 150, 30);
        panel.add(firstNameField);

        JLabel lastNameLabel = new JLabel(messages.getString("lastName"));
        lastNameLabel.setBounds(50, 60, 100, 30);
        panel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(150, 60, 150, 30);
        panel.add(lastNameField);

        JLabel passNumLabel = new JLabel(messages.getString("passNum"));
        passNumLabel.setBounds(50, 100, 120, 30);
        panel.add(passNumLabel);

        JTextField passNumField = new JTextField();
        passNumField.setBounds(150, 100, 150, 30);
        panel.add(passNumField);

        JLabel phoneNumLabel = new JLabel(messages.getString("phoneNum"));
        phoneNumLabel.setBounds(50, 140, 100, 30);
        panel.add(phoneNumLabel);

        JTextField phoneNumField = new JTextField();
        phoneNumField.setBounds(150, 140, 150, 30);
        panel.add(phoneNumField);

        JLabel emailLabel = new JLabel(messages.getString("email"));
        emailLabel.setBounds(50, 180, 100, 30);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 180, 150, 30);
        panel.add(emailField);

        JLabel ageLabel = new JLabel(messages.getString("age"));
        ageLabel.setBounds(50, 220, 100, 30);
        panel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(150, 220, 150, 30);
        panel.add(ageField);

        JLabel usernameLabel = new JLabel(messages.getString("username"));
        usernameLabel.setBounds(50, 260, 100, 30);
        panel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 260, 150, 30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel(messages.getString("pass"));
        passLabel.setBounds(50, 300, 100, 30);
        panel.add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 300, 150, 30);
        panel.add(passwordField);

        JButton createButton = new JButton(messages.getString("createAccount"));
        createButton.setBounds(100, 340, 150, 30);
        createButton.addActionListener(e -> {
            String fName = firstNameField.getText();
            String lName = lastNameField.getText();
            String passNum = passNumField.getText();
            String phoneNum = phoneNumField.getText();
            String email = emailField.getText();
            int age;
            try {
                age = Integer.parseInt(ageField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, messages.getString("invalidAge"));
                return;
            }
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            Client c1 = new Client(lName, fName, passNum, phoneNum, email, age, username, password, 0);

            if (DatabaseController.insertClient(c1)) {
                JOptionPane.showMessageDialog(null, messages.getString("accountCreated"));
                AuthentificationController.insertAccount(username, password);
                new LoginView(locale).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, messages.getString("errorCreatingAccount"));
            }
        });
        panel.add(createButton);

        add(panel);
    }
}
