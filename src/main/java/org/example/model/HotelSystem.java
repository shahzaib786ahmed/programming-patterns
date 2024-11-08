package org.example.model;

import java.util.List;

public class HotelSystem {
    private List<Client> clients;
    private List<Employee> employees;
    private List<Room> availableRooms;
    private List<Room> bookedRooms;

    public HotelSystem(List<Client> clients, List<Employee> employees, List<Room> availableRooms, List<Room> bookedRooms) {
        this.clients = clients;
        this.employees = employees;
        this.availableRooms = availableRooms;
        this.bookedRooms = bookedRooms;
    }

    public void add(Client client) {
        clients.add(client);
    }
    public void add(Employee employee){
        employees.add(employee);
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Room> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(List<Room> availableRooms) {
        this.availableRooms = availableRooms;
    }

    public List<Room> getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(List<Room> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    @Override
    public String toString() {
        return "Hotel System Overview:\n" +
                "-------------------------------------------------\n" +
                " Clients        : " + (clients != null ? clients : "No clients available") + "\n" +
                " Employees      : " + (employees != null ? employees : "No employees available") + "\n" +
                " Available Rooms: " + (availableRooms != null ? availableRooms : "No available rooms") + "\n" +
                " Booked Rooms   : " + (bookedRooms != null ? bookedRooms : "No booked rooms") + "\n" +
                "-------------------------------------------------";
    }
}
