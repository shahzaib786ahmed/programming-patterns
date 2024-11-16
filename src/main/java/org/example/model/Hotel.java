package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

    /**
     *
     */
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
