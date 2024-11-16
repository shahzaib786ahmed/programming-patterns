package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class HotelSystem {
    @Getter
    private static List<Client> clients = new ArrayList<>();
    @Getter
    private static List<Employee> employees = new ArrayList<>();
    @Getter
    public static List<Room> availableRooms = new ArrayList<>();
    @Getter
    public static List<Room> bookedRooms = new ArrayList<>();

    /**
     * Adds a client to the list of clients staying at the hotel
     * @param client to be added in the list of clients at the hotel
     */
    public void add(Client client) {
        clients.add(client);
    }

    /**
     * Adds an employee to the list of employees staying at the hotel
     * @param employee to be added to the list of employees at the hotel
     */
    public void add(Employee employee) {
        employees.add(employee);
    }

    /**
     * Books a hotel room for the client
     * @param client that wants to book a room
     * @param room to be booked for the client
     * @param numberOfNights number of nights staying at the hotel
     * @return the boolean to see if a room is available, if available, book it
     */

    public static boolean bookRoom(Client client, Room room, int numberOfNights) {
        if (room.getRoomStatus() == Room.RoomStatus.AVAILABLE) {
            room.setRoomStatus(Room.RoomStatus.RESERVED);
            room.setPrice(numberOfNights * room.getPrice());
            room.setClient(client);
            availableRooms.remove(room);
            bookedRooms.add(room);
            System.out.println("Room " + room.getRoomNum() + " booked successfully for " + client.getFName() + " " + client.getLName());
            return true;
        } else {
            System.out.println("Room " + room.getRoomNum() + " is not available for booking.");
            return false;
        }
    }

    /**
     * Cancels a hotel room for a customer
     * @param room to be cancelled
     * @return the boolean to see if the reserved room is indeed reserved, if true, cancel it
     */
    public static boolean cancelBooking(Room room) {
        if (room.getRoomStatus() == Room.RoomStatus.RESERVED) {
            room.setRoomStatus(Room.RoomStatus.AVAILABLE);
            bookedRooms.remove(room);
            availableRooms.add(room);
            System.out.println("Room " + room.getRoomNum() + " booking has been canceled.");
            return true;
        } else {
            System.out.println("Room " + room.getRoomNum() + " is not currently reserved.");
            return false;
        }
    }

    /**
     *
     * @return the lists of all available rooms and booked rooms
     */
    @Override
    public String toString() {
        return "Hotel System Overview:\n" +
                "-------------------------------------------------\n" +

                " Available Rooms: " + (availableRooms != null ? availableRooms : "No available rooms") + "\n" +
                " Booked Rooms   : " + (bookedRooms != null ? bookedRooms : "No booked rooms") + "\n" +
                "-------------------------------------------------";
    }
}
