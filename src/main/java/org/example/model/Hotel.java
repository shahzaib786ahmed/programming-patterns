package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Hotel extends Services {
    private int hotel_id;
    private String name;
    private String address;
    private final int totalRooms;

    private static int nextId=1;

    public Hotel(int totalRooms, String address, String name) {
        super();
        this.hotel_id = nextId++;
        this.totalRooms = totalRooms;
        this.address = address;
        this.name = name;
    }

    /**
     * Display details of the hotel
     */
    @Override
    public void displayDetails() {
        String.format(
                "Hotel Details:\n" +
                        "===========================\n" +
                        "Id          : %f\n" +
                        "Name        : %s\n" +
                        "Address     : %s\n" +
                        "Total Rooms : %d\n" +
                        "===========================",
                hotel_id,name, address, totalRooms
        );
    }
}
