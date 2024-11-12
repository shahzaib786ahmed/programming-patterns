package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Room {
    private String roomNum;
    private int capacity;
    private RoomStatus roomStatus;
    private Date checkOutDate;
    private Date checkInDate;
    private int numberOfNights;
    private double price;
    private Employee bookedBy;
    private boolean includesFood;
    private String clientName;

    public enum RoomStatus {
        AVAILABLE, RESERVED;
    }

    public Room(String roomNum, int capacity, RoomStatus roomStatus, Employee bookedBy, String clientName) {
        this.roomNum = roomNum;
        this.capacity = capacity;
        this.roomStatus = RoomStatus.AVAILABLE;
//        this.checkOutDate = checkOutDate;
//        this.checkInDate = checkInDate;
//        this.numberOfNights = numberOfNights;
//        this.price = price;
        this.bookedBy = bookedBy;
//        this.includesFood = includesFood;
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "Room Details:\n" +
                "-------------------------------------------------\n" +
                " Room Number      : " + roomNum + "\n" +
                " Capacity         : " + capacity + "\n" +
                " Status           : " + roomStatus + "\n" +
                " Check-In Date    : " + checkInDate + "\n" +
                " Check-Out Date   : " + checkOutDate + "\n" +
                " Number of Nights : " + numberOfNights + "\n" +
                " Price            : $" + price + "\n" +
                " Booked By        : " + (bookedBy != null ? bookedBy : "Not yet booked") + "\n" +
                " Includes Food    : " + (includesFood ? "Yes" : "No") + "\n" +
                " Client Name      : " + (clientName != null && !clientName.isEmpty() ? clientName : "N/A") + "\n" +
                "-------------------------------------------------";
    }
}
