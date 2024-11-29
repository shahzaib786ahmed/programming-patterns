package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Room {
    private Client client;
    private int roomNum;
    private int capacity;
    private RoomStatus roomStatus;
    private double price;

    public enum RoomStatus {
        AVAILABLE, RESERVED;
    }

    public Room(int roomNum, int capacity, RoomStatus roomStatus, double price) {
        this.client = client;
        this.roomNum = roomNum;
        this.capacity = capacity;
        this.roomStatus = RoomStatus.AVAILABLE;
        this.price = price;
    }

    /**
     * Displays the room details
     *
     * @return the details of the review
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
