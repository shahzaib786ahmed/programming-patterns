package org.example.model;

import java.util.Date;

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

    public enum RoomStatus{
    AVAILABLE,RESERVED;
    }

    public Room(String roomNum, int capacity, RoomStatus roomStatus, Date checkOutDate, Date checkInDate, int numberOfNights, double price, Employee bookedBy, boolean includesFood, String clientName) {
        this.roomNum = roomNum;
        this.capacity = capacity;
        this.roomStatus = roomStatus;
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
        this.numberOfNights = numberOfNights;
        this.price = price;
        this.bookedBy = bookedBy;
        this.includesFood = includesFood;
        this.clientName = clientName;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Employee getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(Employee bookedBy) {
        this.bookedBy = bookedBy;
    }

    public boolean isIncludesFood() {
        return includesFood;
    }

    public void setIncludesFood(boolean includesFood) {
        this.includesFood = includesFood;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
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
