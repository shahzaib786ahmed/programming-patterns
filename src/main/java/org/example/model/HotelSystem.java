package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
public class HotelSystem {
    private List<Client> clients;
    private List<Employee> employees;
    @Getter
    public static List<Room> availableRooms;
    @Getter
    public static List<Room> bookedRooms;

    public HotelSystem() {
        this.clients = clients;
        this.employees = employees;
        this.availableRooms = availableRooms;
        this.bookedRooms = bookedRooms;
    }

    public void add(Client client) {
        clients.add(client);
    }

    public void add(Employee employee) {
        employees.add(employee);
    }

    //Instead of having checkIn and checkOut dates, we should stick to only number of nights staying so it's easy to calculate the stay price
    public static boolean bookRoom(Employee employee, Client client, Room room, int numberOfNights) {
        if (room.getRoomStatus() == Room.RoomStatus.AVAILABLE) {
            room.setRoomStatus(Room.RoomStatus.RESERVED);
            room.setClientName(client.getFName() + " " + client.getLName());
            room.setNumberOfNights(numberOfNights);
            room.setPrice(numberOfNights * room.getPrice());
            room.setBookedBy(employee);
            availableRooms.remove(room);
            bookedRooms.add(room);
            System.out.println("Room " + room.getRoomNum() + " booked successfully for " + client.getFName() + " " + client.getLName());
            return true;
        } else {
            System.out.println("Room " + room.getRoomNum() + " is not available for booking.");
            return false;
        }
    }

    public boolean cancelBooking(Room room) {
        if (room.getRoomStatus() == Room.RoomStatus.RESERVED) {
            room.setRoomStatus(Room.RoomStatus.AVAILABLE);
            room.setClientName(null);
            room.setBookedBy(null);
            bookedRooms.remove(room);
            availableRooms.add(room);
            System.out.println("Room " + room.getRoomNum() + " booking has been canceled.");
            return true;
        } else {
            System.out.println("Room " + room.getRoomNum() + " is not currently reserved.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Hotel System Overview:\n" +
                "-------------------------------------------------\n" +

                " Available Rooms: " + (availableRooms != null ? availableRooms : "No available rooms") + "\n" +
                " Booked Rooms   : " + (bookedRooms != null ? bookedRooms : "No booked rooms") + "\n" +
                "-------------------------------------------------";
    }
}
