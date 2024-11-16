package org.example.dao;

import java.sql.*;

public class FlightDAO {
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
     *  Create Flight Table
     */
    public static void createFlightTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS flights (
                id INTEGER PRIMARY KEY,
                flightNumber TEXT NOT NULL,
                airline TEXT NOT NULL,
                price DOUBLE,
                flightSeatNumber INTEGER,
                departureLocation TEXT NOT NULL,
                arrivalLocation TEXT NOT NULL,
                departureTime TEXT NOT NULL,
                arrivalTime TEXT NOT NULL
            )
        """;

        try (Connection conn = connect();
             Statement stmt = conn != null ? conn.createStatement() : null) {
            if (stmt != null) {
                stmt.execute(sql);
                System.out.println("Flight table created successfully.");
            } else {
                System.out.println("Table creation failed. Connection is null.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Insert new Flight
     * @param flightNumber
     * @param airline
     * @param price
     * @param flightSeatNumber
     * @param departureLocation
     * @param arrivalLocation
     * @param departureTime
     * @param arrivalTime
     */
    public static void insertFlight(String flightNumber, String airline, double price, int flightSeatNumber, String departureLocation, String arrivalLocation, String departureTime, String arrivalTime) {
        String sql = "INSERT INTO flights(flightNumber, airline, price, flightSeatNumber, departureLocation, arrivalLocation, departureTime, arrivalTime) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (pstmt != null) {
                pstmt.setString(1, flightNumber);
                pstmt.setString(2, airline);
                pstmt.setDouble(3, price);
                pstmt.setInt(4, flightSeatNumber);
                pstmt.setString(5, departureLocation);
                pstmt.setString(6, arrivalLocation);
                pstmt.setString(7, departureTime);
                pstmt.setString(8, arrivalTime);
                pstmt.executeUpdate();
                System.out.println("Flight data inserted successfully.");
            } else {
                System.out.println("Insert failed. PreparedStatement is null.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Update Flight info
     * @param id
     * @param newDepartureLocation
     * @param newArrivalLocation
     * @param newDepartureTime
     * @param newArrivalTime
     */
    public static void updateFlight(int id, String newDepartureLocation, String newArrivalLocation, String newDepartureTime, String newArrivalTime) {
        String sql = "UPDATE flights SET departureLocation = ?, arrivalLocation = ?, departureTime = ?, arrivalTime = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newDepartureLocation);
            pstmt.setString(2, newArrivalLocation);
            pstmt.setString(3, newDepartureTime);
            pstmt.setString(4, newArrivalTime);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Flight information updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Delete a Flight by ID
     * @param id
     */
    public static void deleteFlight(int id) {
        String sql = "DELETE FROM flights WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Flight deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Add column to Flight table
     * @param columnName
     * @param columnType
     */
    public static void addColumn(String columnName, String columnType) {
        String sql = "ALTER TABLE flights ADD COLUMN " + columnName + " " + columnType;

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
     * Drop Flight Table
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