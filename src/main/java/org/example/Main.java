package org.example;

import org.example.controller.BookingFlightController;
import org.example.controller.BookingHotelController;
import org.example.controller.CompanySystemController;
import org.example.controller.DatabaseController;
import org.example.model.*;

public class Main {
    //TODO: IF WE RUN DIRECTLY CSM IT DOESNT FIND THE TABLES
    public static void main(String[] args) {
//     MainView mainView = new MainView();
//        MainController mainController = new MainController(mainView);
//      mainView.show();

        ////Testing controller
        //Testing CompanySystemController
        CompanySystemController companySystemController = new CompanySystemController();

        Client client = new Client("Ahmed","Shahzaib","A4325M678","5533257543","shahzaib@gmail.com",20,"ShahzaibisAStar","Shahzaib123", 0);
        Client client2 = new Client("Kharchafi","Aya","B6325I226","4423277543","shahzaib@gmail.com",20,"ShahzaibisAStar","Shahzaib123", 0);
//        companySystemController.addClient(client);
//        companySystemController.addClient(client2);

        Employee employee = new Employee("Kharchafi", "Aya", "123456789", "5143334343", "ayakharchafi@gmail.com", 20, 50);
//        companySystemController.addEmployee(employee);

        Manager manager2 = new Manager("BLS", "Rizz", "C23056789","1234565432","Skibidi@gmail.com",3,80);
//        companySystemController.addManager(manager2);

        Review review = new Review("skibidi@gmail.com", "WOWW GREAT SERVICE", "BEST AGENCY EVER;)");
//        companySystemController.addReview(review);


        //Testing BookingFlightController
        BookingFlightController bookingFlightController = new BookingFlightController();

        Flight flight = new Flight("PK782","Pakistan International Airlines", 1473.58, 254, "YYZ - Lester B Pearson International", "ISB - Islamabad International", "13:00 PM", "12:15 PM");
        Flight flight2 = new Flight("AC810", "Air Canada", 548.23, 108, "YYC - Calgary International", "LAX - Los Angeles International", "10:35","13:05");
//        bookingFlightController.addFlight(flight);
//        bookingFlightController.addFlight(flight2);


        Ticket ticket = new Ticket(flight2, client, "15B", "24 October, 2024", "28 November, 2024", "CREDIT");
//        bookingFlightController.addTicket(ticket);
//        Ticket ticket1;
//        ticket1 = bookingFlightController.purchaseFlightTicket(flight, client, "15B", "24 October, 2024", "28 November, 2024", "CREDIT", client.getEmailAddress(), "1234567891234567");
//        Ticket ticket2;
//        ticket2 = bookingFlightController.purchaseOneWayFlightTicket(flight2, client2, "12C","9 December, 2024", "Loyalty", client.getEmailAddress(), "12345678987654321");
//        Ticket ticket3;
//        ticket3 = bookingFlightController.purchaseEmployeeFlightTicket(flight, employee, "4A", "20 October, 2024", "3 December, 2024", "CREDIT", employee.getEmailAddress(), "9876543212345678");
//        Ticket ticket4;
//        ticket4 = bookingFlightController.purchaseEmployeeOneWayFlightTicket(flight2, employee, "4A", "20 October, 2024", "CREDIT", employee.getEmailAddress(), "9876543212345678");

//        bookingFlightController.removeFlight(flight2.getFlightNumber());
//        bookingFlightController.viewAllFlights();

        bookingFlightController.cancelFlightTicket(ticket);

        //Testing BookingHotelController
        //BookingHotelController bookingHotelController = new BookingHotelController();

//        Hotel hotel = new Hotel(110, "245 Skibidi Av, New York City, New York, USA", "Lame Hotel");
//        bookingHotelController.addHotel(hotel);
//
//        Room room = new Room(24, 1, Room.RoomStatus.AVAILABLE, 40);
//        bookingHotelController.bookRoom(client, room, 4, "1234567898765432");


    }
}
