package org.example.model;

public class Hotel extends Services {
    private String name;
    private String address;
    private final int totalRooms;

    public Hotel(String serviceName, String description, int totalRooms, String address, String name) {
        super(serviceName, description);
        this.totalRooms = totalRooms;
        this.address = address;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    @Override
    public void displayDetails() {
        String.format(
                "Hotel Details:\n" +
                        "===========================\n" +
                        "Name        : %s\n" +
                        "Address     : %s\n" +
                        "Total Rooms : %d\n" +
                        "===========================",
                name, address, totalRooms
        );
    }
}
