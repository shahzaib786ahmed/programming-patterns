package org.example.dao;

import org.example.controller.Employee;

import java.sql.*;

public class EmployeDAO {

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
     * Create Employee Table
     */
    public static void createEmployeeTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS employees (
                id INTEGER PRIMARY KEY,
                lName TEXT NOT NULL,
                fName TEXT NOT NULL,
                passportNumber TEXT NOT NULL,
                phoneNumber TEXT NOT NULL,
                emailAddress TEXT NOT NULL,
                age INTEGER,
                userName TEXT,
                password TEXT,
                discountRate DOUBLE
            )
        """;

        try (Connection conn = connect();
             Statement stmt = conn != null ? conn.createStatement() : null) {
            if (stmt != null) {
                stmt.execute(sql);
                System.out.println("Employee table created successfully.");
            } else {
                System.out.println("Table creation failed. Connection is null.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Insert new employee
     * @param lName last name of the employee
     * @param fName first name of the employee
     * @param passportNumber of the employee
     * @param phoneNumber of the employee
     * @param emailAddress of the employee
     * @param age of the employee
     * @param discountRate for the employee
     */
    public static void insertEmployee(String lName, String fName, String passportNumber, String phoneNumber, String emailAddress, int age,double discountRate) {
        try {
            Employee employee = new Employee(lName, fName, passportNumber, phoneNumber, emailAddress, age, discountRate);

            String sql = "INSERT INTO employees(lName, fName, passportNumber, phoneNumber, emailAddress, age, discountRate) VALUES(?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
                if (pstmt != null) {
                    pstmt.setString(1, employee.getLName());
                    pstmt.setString(2, employee.getFName());
                    pstmt.setString(3, employee.getPassportNum());
                    pstmt.setString(4, employee.getPhoneNumber());
                    pstmt.setString(5, employee.getEmailAddress());
                    pstmt.setInt(6, employee.getAge());
                    pstmt.setDouble(7, employee.getDiscountRate());
                    pstmt.executeUpdate();
                    System.out.println("Employee data inserted successfully.");
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
     * Update Employee info
     * @param id of the employee
     * @param newPhoneNumber of the employee
     * @param newEmailAddress of the employee
     */
    public static void updateEmployee(int id, String newPhoneNumber, String newEmailAddress) {
        String sql = "UPDATE employees SET phoneNumber = ?, emailAddress = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPhoneNumber);
            pstmt.setString(2, newEmailAddress);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Employee information updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Delete an Employee by ID
     * @param id of the employee
     */
    public static void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Add column to employee table
     * @param columnName of the column
     * @param columnType of the column
     */
    public static void addColumn(String columnName, String columnType) {
        String sql = "ALTER TABLE employees ADD COLUMN " + columnName + " " + columnType;

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
     * Drop Employee Table
     * @param tableName of the employee
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
