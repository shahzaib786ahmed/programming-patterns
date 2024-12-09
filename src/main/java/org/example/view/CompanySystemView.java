package org.example.view;

import org.example.controller.CompanySystemController;
import org.example.controller.DatabaseController;
import org.example.model.Client;
import org.example.model.Employee;
import org.example.model.Manager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CompanySystemView extends JFrame {
    private CompanySystemController controller;
    private ResourceBundle messages;

    public CompanySystemView(CompanySystemController controller, Locale locale) {
        this.controller = controller;
        this.messages = ResourceBundle.getBundle("messages", locale);

        setTitle(messages.getString("title"));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize UI components
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));

        JButton addClientButton = new JButton(messages.getString("addClient"));
        JButton addEmployeeButton = new JButton(messages.getString("addEmployee"));
        JButton addManagerButton = new JButton(messages.getString("addManager"));

        JButton deleteClientButton = new JButton(messages.getString("deleteClient"));
        JButton deleteEmployeeButton = new JButton(messages.getString("deleteEmployee"));
        JButton deleteManagerButton = new JButton(messages.getString("deleteManager"));

        JButton displayClientsButton = new JButton(messages.getString("displayClients"));
        JButton displayEmployeesButton = new JButton(messages.getString("displayEmployees"));
        JButton displayManagersButton = new JButton(messages.getString("displayManagers"));

        // Add buttons to the panel
        buttonPanel.add(addClientButton);
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(addManagerButton);

        buttonPanel.add(deleteClientButton);
        buttonPanel.add(deleteEmployeeButton);
        buttonPanel.add(deleteManagerButton);

        buttonPanel.add(displayClientsButton);
        buttonPanel.add(displayEmployeesButton);
        buttonPanel.add(displayManagersButton);

        // Add action listeners for buttons
        addClientButton.addActionListener(e -> controller.addClient(promptForClientDetails()));
        addEmployeeButton.addActionListener(e -> controller.addEmployee(promptForEmployeeDetails()));
        addManagerButton.addActionListener(e -> controller.addManager(promptForManagerDetails()));

        deleteClientButton.addActionListener(e -> controller.deleteClient(promptForClientId()));
        deleteEmployeeButton.addActionListener(e -> controller.deleteEmployee(promptForEmployeeId()));
        deleteManagerButton.addActionListener(e -> controller.deleteManager(promptForManagerId()));

        displayClientsButton.addActionListener(e -> displayClients());
        displayEmployeesButton.addActionListener(e -> displayEmployees());
        displayManagersButton.addActionListener(e -> displayManagers());

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void showDataInPopup(String title, String[] columnNames, Object[][] data) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(600, 400);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(this);

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private void displayClients() {
        String[] columnNames = {
                messages.getString("firstName"),
                messages.getString("lastName"),
                messages.getString("passportNumber"),
                messages.getString("phone"),
                messages.getString("email"),
                messages.getString("age"),
                messages.getString("username")
        };

        List<Client> clients = DatabaseController.queryAllClients();
        if (clients.isEmpty()) {
            JOptionPane.showMessageDialog(this, messages.getString("noClientsFound"));
            return;
        }

        Object[][] data = new Object[clients.size()][columnNames.length];
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            data[i] = new Object[]{
                    client.getFName(),
                    client.getLName(),
                    client.getPassportNum(),
                    client.getPhoneNumber(),
                    client.getEmailAddress(),
                    client.getAge(),
                    client.getUsername()
            };
        }
        showDataInPopup(messages.getString("displayClients"), columnNames, data);
    }

    private void displayEmployees() {
        String[] columnNames = {
                messages.getString("firstName"),
                messages.getString("lastName"),
                messages.getString("passportNumber"),
                messages.getString("phone"),
                messages.getString("email"),
                messages.getString("age"),
                messages.getString("username"),
                messages.getString("discount")
        };

        List<Employee> employees = DatabaseController.queryAllEmployees();
        if (employees.isEmpty()) {
            JOptionPane.showMessageDialog(this, messages.getString("noEmployeesFound"));
            return;
        }

        Object[][] data = new Object[employees.size()][columnNames.length];
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            data[i] = new Object[]{
                    employee.getFName(),
                    employee.getLName(),
                    employee.getPassportNum(),
                    employee.getPhoneNumber(),
                    employee.getEmailAddress(),
                    employee.getAge(),
                    employee.getUsername(),
                    employee.getDiscountRate()
            };
        }
        showDataInPopup(messages.getString("displayEmployees"), columnNames, data);
    }

    private void displayManagers() {
        String[] columnNames = {
                messages.getString("firstName"),
                messages.getString("lastName"),
                messages.getString("passportNumber"),
                messages.getString("phone"),
                messages.getString("email"),
                messages.getString("age"),
                messages.getString("username"),
                messages.getString("discount")
        };

        List<Manager> managers = DatabaseController.queryAllManagers();
        if (managers.isEmpty()) {
            JOptionPane.showMessageDialog(this, messages.getString("noManagersFound"));
            return;
        }

        Object[][] data = new Object[managers.size()][columnNames.length];
        for (int i = 0; i < managers.size(); i++) {
            Manager manager = managers.get(i);
            data[i] = new Object[]{
                    manager.getFName(),
                    manager.getLName(),
                    manager.getPassportNum(),
                    manager.getPhoneNumber(),
                    manager.getEmailAddress(),
                    manager.getAge(),
                    manager.getUsername(),
                    manager.getDiscountRate()
            };
        }
        showDataInPopup(messages.getString("displayManagers"), columnNames, data);
    }

    private Client promptForClientDetails() {
        String fName = JOptionPane.showInputDialog(this, messages.getString("promptClientFirstName"));
        String lName = JOptionPane.showInputDialog(this, messages.getString("promptClientLastName"));
        String passNum = JOptionPane.showInputDialog(this, messages.getString("promptClientPassport"));
        String phone = JOptionPane.showInputDialog(this, messages.getString("promptClientPhone"));
        String email = JOptionPane.showInputDialog(this, messages.getString("promptClientEmail"));
        int age = Integer.parseInt(JOptionPane.showInputDialog(this, messages.getString("promptClientAge")));
        String username = JOptionPane.showInputDialog(this, messages.getString("promptClientUsername"));
        String password = JOptionPane.showInputDialog(this, messages.getString("promptClientPassword"));
        return new Client(lName, fName, passNum, phone, email, age, username, password, 0);
    }

    private Employee promptForEmployeeDetails() {
        String fName = JOptionPane.showInputDialog(this, messages.getString("promptEmployeeFirstName"));
        String lName = JOptionPane.showInputDialog(this, messages.getString("promptEmployeeLastName"));
        String passNum = JOptionPane.showInputDialog(this, messages.getString("promptEmployeePassport"));
        String phone = JOptionPane.showInputDialog(this, messages.getString("promptEmployeePhone"));
        String email = JOptionPane.showInputDialog(this, messages.getString("promptEmployeeEmail"));
        int age = Integer.parseInt(JOptionPane.showInputDialog(this, messages.getString("promptEmployeeAge")));
        String username = JOptionPane.showInputDialog(this, messages.getString("promptEmployeeUsername"));
        String password = JOptionPane.showInputDialog(this, messages.getString("promptEmployeePassword"));
        double discount = Double.parseDouble(JOptionPane.showInputDialog(this, messages.getString("promptEmployeeDiscount")));
        return new Employee(lName, fName, passNum, phone, email, age, username, password, discount);
    }

    private Manager promptForManagerDetails() {
        String fName = JOptionPane.showInputDialog(this, messages.getString("promptManagerFirstName"));
        String lName = JOptionPane.showInputDialog(this, messages.getString("promptManagerLastName"));
        String passNum = JOptionPane.showInputDialog(this, messages.getString("promptManagerPassport"));
        String phone = JOptionPane.showInputDialog(this, messages.getString("promptManagerPhone"));
        String email = JOptionPane.showInputDialog(this, messages.getString("promptManagerEmail"));
        int age = Integer.parseInt(JOptionPane.showInputDialog(this, messages.getString("promptManagerAge")));
        String username = JOptionPane.showInputDialog(this, messages.getString("promptManagerUsername"));
        String password = JOptionPane.showInputDialog(this, messages.getString("promptManagerPassword"));
        double discount = Double.parseDouble(JOptionPane.showInputDialog(this, messages.getString("promptManagerDiscount")));
        return new Manager(lName, fName, passNum, phone, email, age, username, password, discount);
    }

    private Client promptForClientId() {
        String passNum = JOptionPane.showInputDialog(this, messages.getString("promptDeleteClient"));
        return DatabaseController.findClientByPassportNumber(passNum);
    }

    private Employee promptForEmployeeId() {
        String passNum = JOptionPane.showInputDialog(this, messages.getString("promptDeleteEmployee"));
        return DatabaseController.queryEmployeeByPassport(passNum);
    }

    private Manager promptForManagerId() {
        String passNum = JOptionPane.showInputDialog(this, messages.getString("promptDeleteManager"));
        return DatabaseController.queryManagerByPassportNumber(passNum);
    }

}
