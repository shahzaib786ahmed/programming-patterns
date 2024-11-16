package org.example.dao;

import org.example.model.Manager;

import java.sql.*;

public class ManagerDAO {
    /**
     * Connect to SQLite database
     * @return the connection
     */
    private static Connection connect() {
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
     * Create Manager Table
     */
    public static void createManagerTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS managers (
                id INTEGER PRIMARY KEY,
                lName TEXT NOT NULL,
                fName TEXT NOT NULL,
                passportNumber TEXT NOT NULL,
                phoneNumber TEXT NOT NULL,
                emailAddress TEXT NOT NULL,
                age INTEGER,
                discountRate DOUBLE
            )
        """;

        try (Connection conn = connect();
             Statement stmt = conn != null ? conn.createStatement() : null) {
            if (stmt != null) {
                stmt.execute(sql);
                System.out.println("Manager table created successfully.");
            } else {
                System.out.println("Table creation failed. Connection is null.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Insert new manager
     * @param lName
     * @param fName
     * @param passportNumber
     * @param phoneNumber
     * @param emailAddress
     * @param age
     * @param discountRate
     */
    public static void insertManager(String lName, String fName, String passportNumber, String phoneNumber, String emailAddress, int age, double discountRate) {
        try {
            // Validate Manager details
            Manager manager = new Manager(lName, fName, passportNumber, phoneNumber, emailAddress, age, discountRate);

            String sql = "INSERT INTO managers(lName, fName, passportNumber, phoneNumber, emailAddress, age, discountRate) VALUES(?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
                if (pstmt != null) {
                    pstmt.setString(1, manager.getLName());
                    pstmt.setString(2, manager.getFName());
                    pstmt.setString(3, manager.getPassportNum());
                    pstmt.setString(4, manager.getPhoneNumber());
                    pstmt.setString(5, manager.getEmailAddress());
                    pstmt.setInt(6, manager.getAge());
                    pstmt.setDouble(7, manager.getDiscountRate());
                    pstmt.executeUpdate();
                    System.out.println("Manager data inserted successfully.");
                } else {
                    System.out.println("Insert failed. PreparedStatement is null.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Update Manager info
     * @param id
     * @param newPhoneNumber
     * @param newEmailAddress
     */
    public static void updateManager(int id, String newPhoneNumber, String newEmailAddress) {
        String sql = "UPDATE managers SET phoneNumber = ?, emailAddress = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPhoneNumber);
            pstmt.setString(2, newEmailAddress);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Manager information updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Delete a Manager by ID
     * @param id
     */
    public static void deleteManager(int id) {
        String sql = "DELETE FROM managers WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Manager deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Add column to Manager table
     * @param columnName
     * @param columnType
     */
    public static void addColumn(String columnName, String columnType) {
        String sql = "ALTER TABLE managers ADD COLUMN " + columnName + " " + columnType;

        try (Connection conn = connect();
             Statement stmt = conn != null ? conn.createStatement() : null) {
            if (stmt != null) {
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
     * Drop Manager Table
     * @param tableName
     */
    public static void dropTable(String tableName) {
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
}
