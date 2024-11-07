package org.example;

import org.example.model.AgencyDetails;
import org.example.model.Review;

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
    }
}