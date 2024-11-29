package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.DatabaseController;

import java.util.ArrayList;
import java.util.List;

@Setter
public class HotelSystem {

    @Getter
    public static List<Hotel> hotels = new ArrayList<>();
    @Getter
    public static List<Room> availableRooms = new ArrayList<>();
    @Getter
    public static List<Room> reservedRooms = new ArrayList<>();

    private static HotelSystem hotelSystem;

    /**
     * Private constructor for initializing the HotelSystem.
     * This constructor loads the data for hotels, available rooms, and reserved rooms from the database.
     */
    private HotelSystem() {
        this.hotels = DatabaseController.queryAllHotels();
        this.availableRooms =DatabaseController.queryAllAvailableRooms();
        this.reservedRooms = DatabaseController.queryAllReservedRooms();

    }

    /**
     * Returns the singleton instance of the HotelSystem.
     * If the instance does not already exist, it creates a new one.
     * @return the singleton instance of the HotelSystem.
     */
    public static HotelSystem getInstance() {
        if (hotelSystem == null) {
            synchronized (HotelSystem.class) {
                if (hotelSystem == null) {
                    hotelSystem = new HotelSystem();
                }
            }
        }
        return hotelSystem;
    }

    /**
     * Returns a string representation of the Hotel System overview.
     * This includes the list of available rooms and booked rooms in the system.
     *
     * @return a string summary of the hotel system.
     */
    @Override
    public String toString() {
        return "Hotel System Overview:\n" +
                "-------------------------------------------------\n" +

                " Available Rooms: " + (availableRooms != null ? availableRooms : "No available rooms") + "\n" +
                " Booked Rooms   : " + (reservedRooms != null ? reservedRooms : "No booked rooms") + "\n" +
                "-------------------------------------------------";
    }
}
