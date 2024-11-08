package org.example.model;

import java.util.List;

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

    public static int getNumberOfEmployeesManaged() {
        return numberOfEmployeesManaged;
    }

    public List<Employee> getEmployees() {
        return employees;
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
    public Ticket purchaseTicket() {
        return super.purchaseTicket();
    }

    @Override
    public void cancelTicket(Ticket ticket) {
        super.cancelTicket(ticket);
    }

    @Override
    public void changeTicketStatus(Ticket ticket, Status newStatus) {
        super.changeTicketStatus(ticket, newStatus);
    }

    @Override
    public void bookHotelRoom(Hotel hotel, Room room) {
        super.bookHotelRoom(hotel, room);
    }

    @Override
    public void changeRoomStatus(Room room, Room.RoomStatus newStatus) {
        super.changeRoomStatus(room, newStatus);
    }

    @Override
    public void cancelHotelBooking(Room room) {
        super.cancelHotelBooking(room);
    }

    @Override
    public List<Ticket> getRequestedBookings() {
        return super.getRequestedBookings();
    }

    @Override
    public void setDiscountRate(double discountRate) {
        super.setDiscountRate(discountRate);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean isPassportNumValid(String passportNum) {
        return super.isPassportNumValid(passportNum);
    }

    @Override
    public boolean isPhoneNumberValid(String phoneNumber) {
        return super.isPhoneNumberValid(phoneNumber);
    }

    @Override
    public boolean isEmailValid(String emailAddress) {
        return super.isEmailValid(emailAddress);
    }

    @Override
    public int getUserId() {
        return super.getUserId();
    }

    @Override
    public void setUserId(String userId) {
        super.setUserId(userId);
    }

    @Override
    public String getlName() {
        return super.getlName();
    }

    @Override
    public void setlName(String lName) {
        super.setlName(lName);
    }

    @Override
    public String getfName() {
        return super.getfName();
    }

    @Override
    public void setfName(String fName) {
        super.setfName(fName);
    }

    @Override
    public String getPassportNum() {
        return super.getPassportNum();
    }

    @Override
    public void setPassportNum(String passportNum) {
        super.setPassportNum(passportNum);
    }

    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

    @Override
    public String getEmailAddress() {
        return super.getEmailAddress();
    }

    @Override
    public void setEmailAddress(String emailAddress) {
        super.setEmailAddress(emailAddress);
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);
    }
    
    public void setDiscountRate() {
        super.setDiscountRate(20);
    }

    @Override
    public double getDiscountRate() {
        return super.getDiscountRate();
    }

    @Override
    public void displayDetails() {
        System.out.println("Manager: " + super.toString());
    }
}
