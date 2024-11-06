package org.example.model;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    private String flightNum;
    private String clientName;
    private String seatNumber;
    private Status ticketStatus;
    private Employee bookedBy;
    private MyQueue<Operation> operationHistory;
    private String departureDestination;
    private String arrivalDestination;
    private double price;
    private String departureDate;
    private String arrivalDate;

    public Ticket(int ticketId, String flightNum, String clientName, String seatNumber, Status ticketStatus, Employee bookedBy, MyQueue<Operation> operationHistory, String departureDestination, String arrivalDestination, double price, String departureDate, String arrivalDate) {
        this.ticketId = ticketId;
        this.flightNum = flightNum;
        this.clientName = clientName;
        this.seatNumber = seatNumber;
        this.ticketStatus = ticketStatus;
        this.bookedBy = bookedBy;
        this.operationHistory = operationHistory;
        this.departureDestination = departureDestination;
        this.arrivalDestination = arrivalDestination;
        this.price = price;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Status getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Status ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Employee getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(Employee bookedBy) {
        this.bookedBy = bookedBy;
    }

    public MyQueue<Operation> getOperationHistory() {
        return operationHistory;
    }

    public void setOperationHistory(MyQueue<Operation> operationHistory) {
        this.operationHistory = operationHistory;
    }

    public String getDepartureDestination() {
        return departureDestination;
    }

    public void setDepartureDestination(String departureDestination) {
        this.departureDestination = departureDestination;
    }

    public String getArrivalDestination() {
        return arrivalDestination;
    }

    public void setArrivalDestination(String arrivalDestination) {
        this.arrivalDestination = arrivalDestination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}

class Operation implements MyQueue{
    private LocalDateTime operationTime;
    private Employee operatedBy;
    private String description;
}
