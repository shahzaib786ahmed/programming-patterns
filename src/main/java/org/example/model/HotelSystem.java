package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
public class HotelSystem {
    @Getter
    private List<Client> clients = new ArrayList<>();
    @Getter
    private List<Employee> employees=new ArrayList<>();
    @Getter
    public static List<Room> availableRooms = new ArrayList<>();
    @Getter
    public static List<Room> bookedRooms = new ArrayList<>();

    public HotelSystem() {
        this.clients = clients;
        this.employees = employees;
        this.availableRooms = availableRooms;
        this.bookedRooms = bookedRooms;
    }

    /**
     *
     * @param client
     */
    public void add(Client client) {
        clients.add(client);
    }

    /**
     *
     * @param employee
     */
    public void add(Employee employee) {
        employees.add(employee);
    }

    /**
     *
     * @param employee
     * @param client
     * @param room
     * @param numberOfNights
     * @return
     */
    //Instead of having checkIn and checkOut dates, we should stick to only number of nights staying so it's easy to calculate the stay price
    public static boolean bookRoom(Employee employee, Client client, Room room, int numberOfNights) {
        if (room.getRoomStatus() == Room.RoomStatus.AVAILABLE) {
            room.setRoomStatus(Room.RoomStatus.RESERVED);
            room.setPrice(numberOfNights * room.getPrice());
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
     *
     * @param room
     * @return
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
     * @return
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
