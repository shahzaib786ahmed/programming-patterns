package org.example.dao;

import org.example.model.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    /**
     * Connect to SQLite database
     * @return the connection
     */
    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:./src/main/resources/database/data.db";
        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Create a new table
     */
    public static void createNewTableOfClients() {
        String sql = """
                
                    CREATE TABLE IF NOT EXISTS clients (
                    id INTEGER PRIMARY KEY,
                    lName TEXT NOT NULL,
                    fName TEXT NOT NULL,
                    passportNumber TEXT NOT NULL,
                    phoneNumber TEXT NOT NULL,
                    emailAddress TEXT NOT NULL,
                    age INTEGER,
                    userName TEXT,
                    password TEXT,
                    loyaltyPoints INTEGER
                )
                """;

        try (Connection conn = connect();
             Statement stmt = conn != null ? conn.createStatement() : null) {
            if (stmt != null) {
                stmt.execute(sql);
                System.out.println("Table created successfully.");
            } else {
                System.out.println("Table creation failed. Connection is null.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//ask yi how do i check the validation of phonenumber emailadress id without creating a new client
    /**
     * To update a specific client using its id and updating the allowed info
     * @param id client id
     * @param newPhoneNumber new phone number that will replaced the old one
     * @param newEmailAddress new email adress that will replaced the old one
     */
    public static void updateClient(int id, String newPhoneNumber, String newEmailAddress) {
        String sql = "UPDATE clients SET phoneNumber = ?, emailAddress = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPhoneNumber);
            pstmt.setString(2, newEmailAddress);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Client information updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * to delete a specific client using its id
     * @param id client id
     */
    public static void deleteClient(int id) {
        String sql = "DELETE FROM clients WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Client deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * adds a new column to a table
     * @param columnName the name of the new column
     * @param columnType the type of the new column
     */
    public static void addColumn(String columnName, String columnType) {
        String sql = "ALTER TABLE clients ADD COLUMN " + columnName + " " + columnType;

        try (Connection conn = connect();

             Statement stmt = conn != null ? conn.createStatement() : null) {
            if (stmt != null) {
                // Execute the ALTER TABLE query
                stmt.executeUpdate(sql);
                System.out.println("Column " + columnName + " added successfully.");
            } else {
                System.out.println("Add column failed. Connection is null.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * drops a table
     * @param tableName the name of the table to drop
     */
    public static void dropTable(String tableName) {
        // SQL query to drop the table
        String sql = "DROP TABLE IF EXISTS " + tableName;
        try (Connection conn = connect();
             Statement stmt = conn != null ? conn.createStatement() : null) {
            if (stmt != null) {
                stmt.executeUpdate(sql);
                System.out.println("Table " + tableName + " dropped successfully.");
            } else {
                System.out.println("Drop table failed. Connection is null.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Insert a new client in table clients
     * @param lName last name
     * @param fName fist name
     * @param passportNumber passport number
     * @param phoneNumber phone number
     * @param emailAddress email adress
     * @param age age
     * @param userName username for the login
     * @param password passport for the login
     * @param loyaltyPoints loyalty points
     */
    public static void insertClient(String lName, String fName, String passportNumber, String phoneNumber, String emailAddress, int age, String userName, String password, int loyaltyPoints) {
        try {
            // Create a Client object, which will trigger validation
            Client client = new Client(lName, fName, passportNumber, phoneNumber, emailAddress, age, userName, password);

            String sql = "INSERT INTO clients(lName, fName, passportNumber, phoneNumber, emailAddress, age, userName, password, loyaltyPoints) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
                if (pstmt != null) {
                    pstmt.setString(1, client.getLName());
                    pstmt.setString(2, client.getFName());
                    pstmt.setString(3, client.getPassportNum());
                    pstmt.setString(4, client.getPhoneNumber());
                    pstmt.setString(5, client.getEmailAddress());
                    pstmt.setInt(6, client.getAge());
                    pstmt.setString(7, client.getUserName());
                    pstmt.setString(8, client.getPassword());
                    pstmt.setInt(9, client.getLoyaltyPoints());
                    pstmt.executeUpdate();
                    System.out.println("Data inserted successfully.");
                } else {
                    System.out.println("Insert failed. PreparedStatement is null.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            // Catch validation exceptions thrown from the Client constructor
            System.out.println("Error: " + e.getMessage());
        }
    }
}
