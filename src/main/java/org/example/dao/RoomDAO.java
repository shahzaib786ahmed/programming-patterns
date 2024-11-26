package org.example.dao;

import java.sql.*;

public class RoomDAO {
    /**
     *Connect to SQLite database
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
     * Create Room Table
     */
    public static void createRoomTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS rooms (
                id INTEGER PRIMARY KEY,
                roomNum TEXT NOT NULL,
                capacity INTEGER NOT NULL,
                roomStatus TEXT NOT NULL,
                price DOUBLE
            )
        """;

        try (Connection conn = connect();
             Statement stmt = conn != null ? conn.createStatement() : null) {
            if (stmt != null) {
                stmt.execute(sql);
                System.out.println("Room table created successfully.");
            } else {
                System.out.println("Table creation failed. Connection is null.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Insert new Room
     * @param roomNum of the room to be booked
     * @param capacity of the room
     * @param price of the room
     */
    public static void insertRoom(String roomNum, int capacity, double price) {
        String sql = "INSERT INTO rooms(roomNum, capacity, roomStatus, price) VALUES(?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (pstmt != null) {
                pstmt.setString(1, roomNum);
                pstmt.setInt(2, capacity);
                pstmt.setString(3, "AVAILABLE"); // Default room status
                pstmt.setDouble(4, price);
                pstmt.executeUpdate();
                System.out.println("Room data inserted successfully.");
            } else {
                System.out.println("Insert failed. PreparedStatement is null.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Update Room info
     * @param id of the client
     * @param newRoomStatus status of the new room
     * @param newPrice of the new room
     */
    public static void updateRoom(int id, String newRoomStatus, double newPrice) {
        String sql = "UPDATE rooms SET roomStatus = ?, price = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newRoomStatus);
            pstmt.setDouble(2, newPrice);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Room information updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Delete a Room by ID
     * @param id of the client
     */
    public static void deleteRoom(int id) {
        String sql = "DELETE FROM rooms WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Room deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Add column to Room table
     * @param columnName of the column
     * @param columnType of the column
     */
    public static void addColumn(String columnName, String columnType) {
        String sql = "ALTER TABLE rooms ADD COLUMN " + columnName + " " + columnType;

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
     * Drop Room Table
     * @param tableName of the room
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
