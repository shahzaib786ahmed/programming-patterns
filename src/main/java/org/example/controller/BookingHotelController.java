package org.example.controller;

import org.example.model.Hotel;
import org.example.model.HotelSystem;
import org.example.model.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookingHotelController {
    private HotelSystem hotelSystem;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public BookingHotelController() {
        this.hotelSystem = HotelSystem.getInstance();
        initTables();
    }

    private void initTables() {
        DatabaseController.createHotelTable();
        DatabaseController.createRoomTable();
    }

    public void addHotel(Hotel hotel) {
        threadPool.submit(() -> {
            DatabaseController.insertHotel(hotel);
            HotelSystem.getHotels().add(hotel);
        });
    }

    public void removeHotel(Hotel hotel) {
        threadPool.submit(() -> {
            DatabaseController.deleteHotel(hotel.getHotel_id());
            HotelSystem.getHotels().remove(hotel);
        });
    }

    public void viewAllHotels() {
        threadPool.submit(() -> {
           DatabaseController.queryAllHotels();
        });
    }

    public void addRooms(Room room) {
        threadPool.submit(() -> {
            DatabaseController.insertRoom(room);
            HotelSystem.getAvailableRooms().add(room);
        });
    }

    public void removeRoom(Room room) {
        threadPool.submit(() -> {
            DatabaseController.deleteRoom(room.getRoomNum());
            HotelSystem.getAvailableRooms().remove(room);
        });
    }

    public void viewAllRooms() {
        threadPool.submit(() -> {
            DatabaseController.queryAllRooms();
        });
    }
}
