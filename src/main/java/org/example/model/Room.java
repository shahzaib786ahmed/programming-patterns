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
    private double price;

    public enum RoomStatus {
        AVAILABLE, RESERVED;
    }

    public Room(String roomNum, int capacity, double price) {
        this.roomNum = roomNum;
        this.capacity = capacity;
        this.roomStatus = RoomStatus.AVAILABLE;
        this.price = price;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Room Details:\n" +
                "-------------------------------------------------\n" +
                " Room Number      : " + roomNum + "\n" +
                " Capacity         : " + capacity + "\n" +
                " Status           : " + roomStatus + "\n" +
                " Price            : $" + price + "\n" +
                "-------------------------------------------------";
    }
}
