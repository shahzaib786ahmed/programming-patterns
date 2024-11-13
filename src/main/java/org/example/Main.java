package org.example;

import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.example.model.Employee.*;
import static org.example.model.Manager.viewAllBoughtTickets;

public class Main {
    public static void main(String[] args) {

        //Testing the agencyDetails with reviews(add/delete review)
        Review review2 = new Review("aya@gmail.com", "excellent service", "i like it here,would recommend");
        Review review1 = new Review("aya@gmail.com", "excellent service", "i like it here,would recommend");
        AgencyDetails agency = AgencyDetails.getInstance();
        agency.setAgencyName("Vanier Travel Agency");
        agency.setAgencyAddress("123 Main St, Cityville");
        agency.setAgencyPhone("1234567890");
        agency.setAgencyEmail("contact@travelexperts.com");
        agency.addReview(review1);
        agency.addReview(review2);
        agency.deleteReviewById(review2.getReviewId());
        agency.displayAgencyDetails();

        //Testing Flight class
        Flight flight = new Flight("Flight Info", "Flight information from Montreal to Casablanca",
                "AT207", "Royal Air Maroc", 1380.46, 256, "YUL - Montreal Trudeau", "CMN - Casablanca Mohammed V Int'l",
                "9:25 AM", "10:00 PM");
        flight.displayDetails();

        //Testing Ticket/Employe/TicketSystem
        //Adding an employee,client
        Employee employe = new Employee("Kharchafi", "Aya", "123456789", "5143334343", "ayakharchafi@gmail.com", 20, 50);
        Client client = new Client("Lemrani", "Youness", "098765432", "4325678899", "youness@gmail.com", 45, "Youness", "lemrani123");
       Manager manager = new Manager("Tremblay","Francois","123456789","5142346677","francoisTremblay@gmail.con",67,50);
//jlai comment pcq snn ca fait une erreur pcq c null so check why
       //       manager.addEmployee(employe);

        //buying a ticket
        Ticket t1 = purchaseFlightTicket(flight, employe, client, "A89", "2 octobre 2023", "15 octobre 2023");
        Ticket t2 = purchaseFlightTicket(flight, employe, client, "A89", "2 octobre 2023", "15 octobre 2023");
        //canceling a ticket
        cancelFlightTicket(t2);
        //displaying all bought tickets
        viewAllBoughtTickets();
        viewAllCanceledTickets();

        //Purchasing one way ticket
        Ticket t3 = purchaseOneWayFlightTicket(flight,employe,client,"8B","15 february 2024");


        //Purchasing Employee ticket two way
        Ticket t4 = purchaseEmployeeFlightTicket(flight, employe,"30F","12 January 2024", "15 March 2024");


        //Purchasing Employee ticket one way
        Ticket t5 = purchaseEmployeeOneWayFlightTicket(flight,employe,"24A","25 August 2024");


        HotelSystem hotelSystem = new HotelSystem();
        hotelSystem.add(client);
        hotelSystem.add(employe);
        hotelSystem.add(manager);
        Room room = new Room("A1",2,89 );
        Room room2 = new Room("A1",2, 89);
        hotelSystem.availableRooms.add(room);
        hotelSystem.availableRooms.add(room2);
        bookRoomForClient(employe, client,room,2);

        System.out.println( hotelSystem.toString());//display is weird need to be simplified but works!





    }
}