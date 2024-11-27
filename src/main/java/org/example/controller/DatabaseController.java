package org.example.controller;

import org.example.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DatabaseController {
    //TODO:ADD THE INSERT METHOD WITH THE OBJECTS
    private static final String DATABASE_URL = "jdbc:sqlite:./src/main/resources/database/data.db";
    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = LOCK.writeLock();
    private static final ReentrantReadWriteLock.ReadLock READ_LOCK = LOCK.readLock();

    private static Connection connect() {
        try {
            return DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    //ABOUT CLIENTS
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
     * @param newPhoneNumber new phone number that will replace the old one
     * @param newEmailAddress new email adress that will replace the old one
     */
    public static void updateClient(int id, String newPhoneNumber, String newEmailAddress) {
     WRITE_LOCK.lock();
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
        }finally{
            WRITE_LOCK.unlock();
        }
    }

    /**
     * to delete a specific client using its id
     * @param id client id
     */
    public static void deleteClient(int id) {
        WRITE_LOCK.lock();
        String sql = "DELETE FROM clients WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Client deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            WRITE_LOCK.unlock();
        }
    }
    /**
     * Insert a new client in table clients
     * @param lName last name of the client
     * @param fName first name of the client
     * @param passportNumber passport number of the client
     * @param phoneNumber phone number of the client
     * @param emailAddress email address of the client
     * @param age age of the client
     * @param userName username for the login of the client
     * @param password passport for the login of the client
     * @param loyaltyPoints loyalty points of the client
     */
    public static void insertClient(String lName, String fName, String passportNumber, String phoneNumber, String emailAddress, int age, String userName, String password, int loyaltyPoints) {
    WRITE_LOCK.lock();
            String sql = "INSERT INTO clients(lName, fName, passportNumber, phoneNumber, emailAddress, age, userName, password, loyaltyPoints) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt =  conn.prepareStatement(sql) ) {

                    pstmt.setString(1, lName);
                    pstmt.setString(2, fName);
                    pstmt.setString(3, passportNumber);
                    pstmt.setString(4, phoneNumber);
                    pstmt.setString(5, emailAddress);
                    pstmt.setInt(6, age);
                    pstmt.setString(7, userName);
                    pstmt.setString(8, password);
                    pstmt.setInt(9, loyaltyPoints);
                    pstmt.executeUpdate();
                    System.out.println("Data inserted successfully.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }finally{
                WRITE_LOCK.unlock();
            }
        }


    public static void insertClient(Client client){
        String lName = client.getLName();
        String fName = client.getFName();
        String passportNumber = client.getPassportNum();
        String phoneNumber = client.getPhoneNumber();
        String emailAddress = client.getEmailAddress();
        int age = client.getAge();
        String userName = client.getUserName();
        String password = client.getPassword();
        int loyaltyPoints = client.getLoyaltyPoints();

        insertClient(lName,fName,passportNumber,phoneNumber,emailAddress,age,userName,password,loyaltyPoints);
    }
    public static List<Client> queryAllClients(){
       READ_LOCK.lock();

       String sql = """
               SELECT * FROM Clients
               """;
       List<Client> clients = new ArrayList<>();
       try(Connection connection = connect();
       Statement statement = connection.createStatement();
       ResultSet resultSet = statement.executeQuery(sql)){
           while(resultSet.next()){
               String lName = resultSet.getString("lName");
               String fName = resultSet.getString("fName");
               String passportNumber = resultSet.getString("passportNumber");
               String phoneNumber = resultSet.getString("phoneNumber");
               String emailAddress = resultSet.getString("emailAddress");
               int age = resultSet.getInt("age");
               String userName = resultSet.getString("userName");
               String password = resultSet.getString("password");
               int loyaltyPoints = resultSet.getInt("loyaltyPoints");

               clients.add(new Client(lName,fName, passportNumber, phoneNumber, emailAddress, age, userName, password));
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }finally{
           READ_LOCK.unlock();
       }
       return clients;
    }

    //ABOUT EMPLOYEES
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
             Statement stmt =conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Employee table created successfully.");

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
        WRITE_LOCK.lock();
            String sql = "INSERT INTO employees(lName, fName, passportNumber, phoneNumber, emailAddress, age, discountRate) VALUES(?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, lName);
                    pstmt.setString(2, fName);
                    pstmt.setString(3, passportNumber);
                    pstmt.setString(4, phoneNumber);
                    pstmt.setString(5, emailAddress);
                    pstmt.setInt(6, age);
                    pstmt.setDouble(7, discountRate);
                    pstmt.executeUpdate();
                    System.out.println("Employee data inserted successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }finally{
                WRITE_LOCK.unlock();
            }
    }
    public static void insertEmployee(Employee employee){
        String lName = employee.getLName();
        String fName = employee.getFName();
        String passportNumber = employee.getPassportNum();
        String phoneNumber = employee.getPhoneNumber();
        String emailAddress = employee.getEmailAddress();
        int age = employee.getAge();
        double discountRate = employee.getDiscountRate();

        insertEmployee(lName, fName,passportNumber,phoneNumber,emailAddress,age,discountRate);
    }
    /**
     * Update Employee info
     * @param id of the employee
     * @param newPhoneNumber of the employee
     * @param newEmailAddress of the employee
     */
    public static void updateEmployee(int id, String newPhoneNumber, String newEmailAddress) {
     WRITE_LOCK.lock();
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
        }finally{
            WRITE_LOCK.unlock();
        }
    }
    /**
     * Delete an Employee by ID
     * @param id of the employee
     */
    public static void deleteEmployee(int id) {
        WRITE_LOCK.lock();
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            WRITE_LOCK.unlock();
        }
    }

    public static List<Employee> queryAllEmployees() {
        READ_LOCK.lock();

        String sql = """
               SELECT * FROM employees
               """;
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()) {
                String lName = resultSet.getString("lName");
                String fName = resultSet.getString("fName");
                String passportNumber = resultSet.getString("passportNumber");
                String phoneNumber = resultSet.getString("phoneNumber");
                String emailAddress = resultSet.getString("emailAddress");
                int age = resultSet.getInt("age");
                double discountRate = resultSet.getDouble("discountRate");

                employees.add(new Employee(lName, fName, passportNumber, phoneNumber, emailAddress, age, discountRate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            READ_LOCK.unlock();
        }
        return employees;
    }
    //ABOUT FLIGHTS
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
     * @param flightNumber of the flight
     * @param airline name of the flight
     * @param price of the flight
     * @param flightSeatNumber of the flight
     * @param departureLocation of the flight departing from
     * @param arrivalLocation of the flight arriving to
     * @param departureTime of the flight
     * @param arrivalTime of the flight
     */
    public static void insertFlight(String flightNumber, String airline, double price, int flightSeatNumber, String departureLocation, String arrivalLocation, String departureTime, String arrivalTime) {
       WRITE_LOCK.lock();

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
        }finally{
            WRITE_LOCK.unlock();
        }
    }
    public static void insertFlight(Flight flight){
     String flightNumber = flight.getFlightNumber();
     String airline = flight.getAirline();
     double price = flight.getPrice();
     int flightSeatNumber = flight.getFlightSeatNumber();
     String departureLocation = flight.getDepartureLocation();
     String arrivalLocation = flight.getArrivalLocation();
     String departureTime = flight.getDepartureTime();
     String arrivalTime = flight.getArrivalTime();

     insertFlight(flightNumber,airline,price,flightSeatNumber,departureLocation,arrivalLocation,departureTime,arrivalTime);
    }
    /**
     * Update Flight info
     * @param id of the flight
     * @param newDepartureLocation of the flight departing from
     * @param newArrivalLocation of the flight arriving to
     * @param newDepartureTime of the flight
     * @param newArrivalTime of the flight
     */
    public static void updateFlight(int id, String newDepartureLocation, String newArrivalLocation, String newDepartureTime, String newArrivalTime) {
    WRITE_LOCK.lock();
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
        }finally{
            WRITE_LOCK.unlock();
        }
    }
    /**
     * Delete a Flight by ID
     * @param id of the flight
     */
    public static void deleteFlight(int id) {
        WRITE_LOCK.lock();
        String sql = "DELETE FROM flights WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Flight deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            WRITE_LOCK.unlock();
        }
    }

    /**
     *
     * @return
     */
    public static List<Flight> queryAllFlight(){
        READ_LOCK.lock();
        String sql ="SELECT * FROM flights";

        List<Flight> flights = new ArrayList<>();
        try(Connection connection = connect();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){
            while(resultSet.next()){
               String flightNumber = resultSet.getString("flightNumber");
               String airline = resultSet.getString("airline");
               double price = resultSet.getDouble("price");
               int flightSeatNumber = resultSet.getInt("flightSeatNumber");
               String departureLocation = resultSet.getString("departureLocation");
               String arrivalLocation = resultSet.getString("arrivalLocation");
               String departureTime = resultSet.getString("departureTime");
               String arrivalTime = resultSet.getString("arrivalTime");

               flights.add(new Flight(flightNumber,airline,price,flightSeatNumber,departureLocation,arrivalLocation,departureTime,arrivalTime));

            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally{
            READ_LOCK.unlock();
        }
        return flights;
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
     * @param lName last name of the manager
     * @param fName first name of the manager
     * @param passportNumber of the manager
     * @param phoneNumber of the manager
     * @param emailAddress of the manager
     * @param age of the manager
     * @param discountRate for the manager
     */
    public static void insertManager(String lName, String fName, String passportNumber, String phoneNumber, String emailAddress, int age, double discountRate) {
      WRITE_LOCK.lock();
            // Validate Manager details
            String sql = "INSERT INTO managers(lName, fName, passportNumber, phoneNumber, emailAddress, age, discountRate) VALUES(?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, lName);
                    pstmt.setString(2, fName);
                    pstmt.setString(3, passportNumber);
                    pstmt.setString(4, phoneNumber);
                    pstmt.setString(5, emailAddress);
                    pstmt.setInt(6, age);
                    pstmt.setDouble(7, discountRate);
                    pstmt.executeUpdate();
                    System.out.println("Manager data inserted successfully.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }finally{
                WRITE_LOCK.unlock();
            }
    }
    public static void insertManager(Manager manager){
           String lName = manager.getLName();
           String fName = manager.getFName();
           String passportNumber = manager.getPhoneNumber();
           String phoneNumber = manager.getPhoneNumber();
           String emailAddress = manager.getEmailAddress();
           int age = manager.getAge();
           double discountRate = manager.getDiscountRate();

           insertManager(lName,fName,passportNumber,phoneNumber,emailAddress,age,discountRate);
    }
    /**
     * Update Manager info
     * @param id of the manager
     * @param newPhoneNumber of the manager
     * @param newEmailAddress of the manager
     */
    public static void updateManager(int id, String newPhoneNumber, String newEmailAddress) {
    WRITE_LOCK.lock();
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
        }finally{
            WRITE_LOCK.unlock();
        }
    }
    /**
     * Delete a Manager by ID
     * @param id of the manager
     */
    public static void deleteManager(int id) {
        WRITE_LOCK.lock();
        String sql = "DELETE FROM managers WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Manager deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            WRITE_LOCK.unlock();
        }
    }

    /**
     *
     * @return
     */
    public static List<Manager> queryAllManagers(){
        READ_LOCK.lock();

        String sql ="SELECT * FROM managers";
        List<Manager> managers = new ArrayList<>();
        try(Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                String lName = resultSet.getString("lName");
                String fName = resultSet.getString("fName");
                String passportNumber = resultSet.getString("passportNumber");
                String phoneNumber = resultSet.getString("phoneNumber");
                String emailAddress =resultSet.getString("emailAddress");
                int age =resultSet.getInt("age");
                double discountRate = resultSet.getDouble("discountRate");
                managers.add(new Manager(lName,fName,passportNumber,phoneNumber,emailAddress,age,discountRate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            READ_LOCK.unlock();
        }
        return managers;
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
                price DOUBLE NOT NULL
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
    public static void insertRoom(String roomNum, int capacity, Room.RoomStatus roomStatus, double price) {
     WRITE_LOCK.lock();
        String sql = "INSERT INTO rooms(roomNum, capacity, roomStatus, price) VALUES(?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (pstmt != null) {
                pstmt.setString(1, roomNum);
                pstmt.setInt(2, capacity);
                pstmt.setString(3, roomStatus.name());
                pstmt.setDouble(4, price);
                pstmt.executeUpdate();
                System.out.println("Room data inserted successfully.");
            } else {
                System.out.println("Insert failed. PreparedStatement is null.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            WRITE_LOCK.unlock();
        }
    }
    public static void insertRoom(Room room){
          String roomNum = room.getRoomNum();
          int capacity = room.getCapacity();
          Room.RoomStatus roomStatus = room.getRoomStatus();
          double price = room.getPrice();

          insertRoom(roomNum,capacity,roomStatus,price);
    }
    /**
     * Update Room info
     * @param id of the client
     * @param newRoomStatus status of the new room
     * @param newPrice of the new room
     */
    public static void updateRoom(int id,Room.RoomStatus newRoomStatus, double newPrice) {
        WRITE_LOCK.lock();
        String sql = "UPDATE rooms SET roomStatus = ?, price = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newRoomStatus.name());
            pstmt.setDouble(2, newPrice);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Room information updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            WRITE_LOCK.unlock();
        }
    }
    /**
     * Delete a Room by ID
     * @param id of the client
     */
    public static void deleteRoom(int id) {
     WRITE_LOCK.lock();
        String sql = "DELETE FROM rooms WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Room deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            WRITE_LOCK.unlock();
        }
    }

    /**
     *
     * @return
     */
    public static List<Room> queryAllRooms(){
        READ_LOCK.lock();
        String sql ="SELECT * FROM rooms";
        List<Room> rooms = new ArrayList<>();
        try(Connection conn = connect();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                    String roomNum= resultSet.getString("roomNum");
                    int capacity = resultSet.getInt("capacity");
                     String roomStatusStr = resultSet.getString("roomStatus");
                    Room.RoomStatus roomStatus = Room.RoomStatus.valueOf(roomStatusStr);
                    double price = resultSet.getDouble("price");
                    rooms.add(new Room(roomNum,capacity,roomStatus,price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            READ_LOCK.unlock();
        }
        return rooms;
    }
    public static void createHotel(){
               String sql = """
                       CREATE TABLE IF NOT EXISTS hotels
                       hotel_id INTEGER PRIMARY KEY,
                       totalRooms INTEGER NOT NULL,
                       address TEXT NOT NULL,
                       name TEXT NOT NULL
                       """;
      try (Connection conn = connect();
                      Statement stmt = conn.createStatement()) {
                  stmt.execute(sql);
              } catch (SQLException e) {
                  throw new RuntimeException(e);
              }
    }

    public static void insertHotel(int hotel_id, int totalRooms, String address, String name){
          WRITE_LOCK.lock();
          String sql = """
                  INSERT INTO hotels (hotel_id, totalRooms, address, name) VALUES (?,?,?,?)
                  """;


          



    }


}
