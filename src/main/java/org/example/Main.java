package org.example;

import org.example.model.AgencyDetails;
import org.example.model.Flight;
import org.example.model.Review;
import org.example.model.Ticket;

public class Main {
    public static void main(String[] args) {
        //Testing the agencyDetails with reviews(add/delete review)
        Review review2 = new Review("aya@gmail.com","excellent service","i like it here,would recommend");
        Review review1 = new Review("aya@gmail.com","excellent service","i like it here,would recommend");
        AgencyDetails agency= AgencyDetails.getInstance();
        agency.setAgencyName("Vanier Travel Agency");
        agency.setAgencyAddress("123 Main St, Cityville");
        agency.setAgencyPhone("1234567890");
        agency.setAgencyEmail("contact@travelexperts.com");
        agency.addReview(review1);
        agency.addReview(review2);
        agency.deleteReviewById(review2.getReviewId());
        agency.displayAgencyDetails();

        //Testing Flight class
        Flight flight = new Flight("Flight Info","Flight information from Montreal to Casablanca",
                "AT207","Royal Air Maroc",1380.46,256,"YUL - Montreal Trudeau", "CMN - Casablanca Mohammed V Int'l",
                "9:25 AM","10:00 PM");
        flight.displayDetails();
    }
}