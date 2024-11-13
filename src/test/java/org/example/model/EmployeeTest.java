package org.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest{

    @Test
    public void testPurchaseFlightTicket() {
        Flight flight = new Flight("Flight Info", "Flight information from Montreal to Casablanca",
                "AT207", "Royal Air Maroc", 1380.46, 256, "YUL - Montreal Trudeau", "CMN - Casablanca Mohammed V Int'l",
                "9:25 AM", "10:00 PM");
        Employee employee = new Employee("Kharchafi", "Aya", "123456789", "5143334343", "ayakharchafi@gmail.com", 20, 50);
        Client client = new Client("Ahmed", "Shahzaib", "098765432", "4325678899", "youness@gmail.com", 45, "Youness", "lemrani123");

        Ticket expResult = new Ticket(flight,employee,client,"13A","12 January 2024", "15 March 2024");
        Ticket result = Employee.purchaseFlightTicket(flight,employee,client,"13A","12 January 2024", "15 March 2024");
        assertEquals(expResult, result);
    }

    @Test
    public void testPurchaseEmployeeFlightTicket() {
        Flight flight = new Flight("Flight Info", "Flight information from Montreal to Casablanca",
                "AT207", "Royal Air Maroc", 1380.46, 256, "YUL - Montreal Trudeau", "CMN - Casablanca Mohammed V Int'l",
                "9:25 AM", "10:00 PM");
        Employee employee = new Employee("Kharchafi", "Aya", "123456789", "5143334343", "ayakharchafi@gmail.com", 20, 50);

        Ticket expResult = new Ticket(flight,employee,"13A","12 January 2024", "15 March 2024");
        Ticket result = Employee.purchaseEmployeeFlightTicket(flight,employee,"13A","12 January 2024", "15 March 2024");
        assertEquals(expResult, result);
    }

    @Test
    public void testPurchaseOneWayFlightTicket() {
        Flight flight = new Flight("Flight Info", "Flight information from Montreal to Casablanca",
                "AT207", "Royal Air Maroc", 1380.46, 256, "YUL - Montreal Trudeau", "CMN - Casablanca Mohammed V Int'l",
                "9:25 AM", "10:00 PM");
        Employee employee = new Employee("Kharchafi", "Aya", "123456789", "5143334343", "ayakharchafi@gmail.com", 20, 50);
        Client client = new Client("Ahmed", "Shahzaib", "098765432", "4325678899", "youness@gmail.com", 45, "Youness", "lemrani123");

        Ticket expResult = new Ticket(flight,employee,client,"13A","12 January 2024");
        Ticket result = Employee.purchaseOneWayFlightTicket(flight,employee,client,"13A","12 January 2024");
        assertEquals(expResult, result);
    }

    @Test
    public void testPurchaseEmployeeOneWayFlightTicket() {
        Flight flight = new Flight("Flight Info", "Flight information from Montreal to Casablanca",
                "AT207", "Royal Air Maroc", 1380.46, 256, "YUL - Montreal Trudeau", "CMN - Casablanca Mohammed V Int'l",
                "9:25 AM", "10:00 PM");
        Employee employee = new Employee("Kharchafi", "Aya", "123456789", "5143334343", "ayakharchafi@gmail.com", 20, 50);

        Ticket expResult = new Ticket(flight,employee,"13A","12 January 2024");
        Ticket result = Employee.purchaseEmployeeOneWayFlightTicket(flight,employee,"13A","12 January 2024");
        assertEquals(expResult, result);
    }
}