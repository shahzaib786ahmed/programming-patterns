package org.example.model;

import org.example.controller.Employee;
import org.example.controller.HotelSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelSystemTest {
    //TODO:ADD MORE TESTS
    @Test
    void bookRoom() {
        Employee employee = new Employee("Kharchafi", "Aya", "123456789", "5143334343", "ayakharchafi@gmail.com", 20, 50);
        Client client = new Client("Ahmed", "Shahzaib", "098765432", "4325678899", "youness@gmail.com", 45, "Youness", "lemrani123");
        Room room = new Room(client, "89",2,84.98);
        int numOfNights = 4;

        boolean expResult = true;
        boolean result = HotelSystem.bookRoom(client, room, numOfNights);
        assertEquals(expResult, result);
    }

    @Test
    void cancelBooking() {
        Employee employee = new Employee("Kharchafi", "Aya", "123456789", "5143334343", "ayakharchafi@gmail.com", 20, 50);
        Client client = new Client("Ahmed", "Shahzaib", "098765432", "4325678899", "youness@gmail.com", 45, "Youness", "lemrani123");
        Room room = new Room(client, "89",2,84.98);
        int numOfNights = 4;
        HotelSystem.bookRoom(client, room, numOfNights);

        boolean expResult = true;
        boolean result = HotelSystem.cancelBooking(room);
        assertEquals(expResult, result);
    }
}