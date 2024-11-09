package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Manager extends Employee{
    private static int numberOfEmployeesManaged;
    private static List<Employee> employees;

    public Manager(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, double discountRate) {
        super(lName, fName, passportNum, phoneNumber, emailAddress, age, discountRate);
    }

    public void assignTo(Ticket ticket, Employee employee) {
        if (ticket.getBookedBy().getUserId() != employee.getUserId()) {
            employee.getRequestedBookings().add(ticket);
        } else {
            throw new IllegalArgumentException("This ticket is already assigned to this employee.");
        }
    }

    public Employee addEmployee(String lName, String fName, String passportNum,String phoneNumber, String emailAddress, int age) {
        Employee newEmployee = new Employee(lName, fName, passportNum, phoneNumber, emailAddress, age,10);
        if (employees.contains(newEmployee)) {
            throw new IllegalArgumentException("Employee already exists.");
        } else {
            numberOfEmployeesManaged++;
            return newEmployee;
        }
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        numberOfEmployeesManaged--;
    }

    @Override
    public Ticket fetchNewTicket() {
        return super.fetchNewTicket();
    }

    @Override
    public void viewAllTickets(List<Ticket> tickets) {
        super.viewAllTickets(tickets);
    }

    @Override
    public void changeTicketStatus(Ticket ticket, Status newStatus) {
        super.changeTicketStatus(ticket, newStatus);
    }

    @Override
    public void bookRoomForClient(HotelSystem hotelSystem, Client client, Room room) {
        super.bookRoomForClient(hotelSystem, client, room);
    }

    @Override
    public void cancelRoomBooking(HotelSystem hotelSystem, Room room) {
        super.cancelRoomBooking(hotelSystem, room);
    }

    @Override
    public void displayDetails() {
        System.out.println("Manager: " + super.toString());
    }
}
