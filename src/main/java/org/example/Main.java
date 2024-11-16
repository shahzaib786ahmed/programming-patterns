package org.example;

import org.example.dao.*;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.example.model.Employee.*;
import static org.example.model.Manager.viewAllBoughtTickets;

public class Main {
    public static void main(String[] args) {
//TESTING THE ROOMDAO
//        // Create Room table
//        RoomDAO.createRoomTable();
//
//        // Insert a new Room
//        RoomDAO.insertRoom("101", 2, 120.0);
//
//        // Update a Room's status and price
//        RoomDAO.updateRoom(1, "OCCUPIED", 150.0);
//
//        // Delete a Room
//        RoomDAO.deleteRoom(1);
//
//        //Drop Room table
//        RoomDAO.dropTable("rooms");
//
        //TESTING THE FLIGHTDAO
//        // Create Flight table
//        FlightDAO.createFlightTable();
//
//        // Insert a new Flight
//        FlightDAO.insertFlight("AB123", "Airline A", 500.0, 150, "New York", "London", "2025-01-01 10:00", "2025-01-01 18:00");
//
//        // Update a Flight's departure and arrival locations and times
//        FlightDAO.updateFlight(1, "Los Angeles", "Paris", "2025-02-01 10:00", "2025-02-01 18:00");
//
//        // Delete a Flight
//        FlightDAO.deleteFlight(1);
//
//        //Drop Flight table
//        FlightDAO.dropTable("flights");
//
//TESTING THE MANAGERDAO
//        // Create Manager table
//        ManagerDAO.createManagerTable();
//
//        // Insert a 1RST Manager
//        ManagerDAO.insertManager("Doe",
//                "Jane",
//                "B67890867",
//                "5145559876",
//                "janedoe@example.com",
//                35,
//                20.0);
//        // Insert a 2ND Manager
//        ManagerDAO.insertManager("Doe",
//                "Jane",
//                "B67890856",
//                "5145559876",
//                "janedoe@example.com",
//                35,
//                20.0);
//
//        // Update a Manager's phone number and email address
//        ManagerDAO.updateManager(1, "555-4321", "jane.doe@newemail.com");
//
//        // Delete a Manager
//        ManagerDAO.deleteManager(1);
//        ManagerDAO.dropTable("managers");


        // TESTING THE EMPLOYEDAO
//        // Create the employee table
//        EmployeDAO.createEmployeeTable();
//
//        // Insert the first two employees
//        EmployeDAO.insertEmployee(
//                "Aya",
//                "John",
//                "AB1234568",
//                "1234567890",
//                "johndoe@example.com",
//                30,
//                100
//        );
//        EmployeDAO.insertEmployee(
//                "Smith",
//                "Jane",
//                "XY7890128",
//                "9876543210",
//                "janesmith@example.com",
//                25,
//                200
//        );
//        // Add a new column
//        EmployeDAO.addColumn("additionalInfo", "TEXT");
//
//        // Update employee information
//        EmployeDAO.updateEmployee(2, "5148153354", "ayakharchafi@gmail.com");
//
//        // Delete a specific employee(example by ID)
//        EmployeDAO.deleteEmployee(1); // Deletes employe with ID 1
//
//        // Drop the employees table (this deletes all data)
//        EmployeDAO.dropTable("employees");

//        // TESTING THE CLIENTDAO
//        // Create the clients table
//        ClientDAO.createNewTableOfClients();
//
//        // Insert the first client
//        ClientDAO.insertClient(
//                "Aya",
//                "John",
//                "AB123456",
//                "+1234567890",
//                "johndoe@example.com",
//                30,
//                "johndoe",
//                "password123",
//                100
//        );
//
//        // Insert a second client
//        ClientDAO.insertClient(
//                "Smith",
//                "Jane",
//                "XY789012",
//                "+9876543210",
//                "janesmith@example.com",
//                25,
//                "janesmith",
//                "securepassword",
//                200
//        );
//        // Add a new column
//        ClientDAO.addColumn("additionalInfo", "TEXT");
//
//        // Update client information (example)
//        ClientDAO.updateClient(2, "5148153354", "ayakharchafi@gmail.com");
//
//        // Delete a specific client (example by ID)
//        ClientDAO.deleteClient(1); // Deletes client with ID 1
//
//        // Drop the clients table (this deletes all data)
//        ClientDAO.dropTable("clients");


//
//        //Testing the agencyDetails with reviews(add/delete review)
//        Review review2 = new Review("aya@gmail.com", "excellent service", "i like it here,would recommend");
//        Review review1 = new Review("aya@gmail.com", "excellent service", "i like it here,would recommend");
//        AgencyDetails agency = AgencyDetails.getInstance();
//        agency.setAgencyName("Vanier Travel Agency");
//        agency.setAgencyAddress("123 Main St, Cityville");
//        agency.setAgencyPhone("1234567890");
//        agency.setAgencyEmail("contact@travelexperts.com");
//        agency.addReview(review1);
//        agency.addReview(review2);
//        agency.deleteReviewById(review2.getReviewId());
//        agency.displayAgencyDetails();
//
//        //Testing Flight class
//        Flight flight = new Flight("Flight Info", "Flight information from Montreal to Casablanca",
//                "AT207", "Royal Air Maroc", 1380.46, 256, "YUL - Montreal Trudeau", "CMN - Casablanca Mohammed V Int'l",
//                "9:25 AM", "10:00 PM");
//        flight.displayDetails();
//
//        //Testing Ticket/Employe/TicketSystem
//        //Adding an employee,client
//        Employee employe = new Employee("Kharchafi", "Aya", "123456789", "5143334343", "ayakharchafi@gmail.com", 20, 50);
//        Client client = new Client("Lemrani", "Youness", "098765432", "4325678899", "youness@gmail.com", 45, "Youness", "lemrani123");
//       Manager manager = new Manager("Tremblay","Francois","123456789","5142346677","francoisTremblay@gmail.con",67,50);
////jlai comment pcq snn ca fait une erreur pcq c null so check why
//       //       manager.addEmployee(employe);
//
//        //buying a ticket
//        Ticket t1 = purchaseFlightTicket(flight, employe, client, "A89", "2 octobre 2023", "15 octobre 2023");
//        Ticket t2 = purchaseFlightTicket(flight, employe, client, "A89", "2 octobre 2023", "15 octobre 2023");
//        //canceling a ticket
//        cancelFlightTicket(t2);
//        //displaying all bought tickets
//        viewAllBoughtTickets();
//        viewAllCanceledTickets();
//
//        //Purchasing one way ticket
//        Ticket t3 = purchaseOneWayFlightTicket(flight,employe,client,"8B","15 february 2024");
//
//
//        //Purchasing Employee ticket two way
//        Ticket t4 = purchaseEmployeeFlightTicket(flight, employe,"30F","12 January 2024", "15 March 2024");
//
//
//        //Purchasing Employee ticket one way
//        Ticket t5 = purchaseEmployeeOneWayFlightTicket(flight,employe,"24A","25 August 2024");
//
//
//        HotelSystem hotelSystem = new HotelSystem();
//        hotelSystem.add(client);
//        hotelSystem.add(employe);
//        hotelSystem.add(manager);
//        Room room = new Room("A1",2,89 );
//        Room room2 = new Room("A1",2, 89);
//        hotelSystem.availableRooms.add(room);
//        hotelSystem.availableRooms.add(room2);
//        bookRoomForClient(employe, client,room,2);
//
//        System.out.println( hotelSystem.toString());//display is weird need to be simplified but works!
//
//
    }
}
