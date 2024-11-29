package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgencyDetailsTest {
//TODO:ADD MORE TESTS
    @Test
    void deleteReviewById() {
        Review r1 = new Review("shahzaib@gmail.com","Good service!","WOWWW GREAT AMAZING CUSTOMER SERVICE");
        String reviewID = r1.getReviewId();
        AgencyDetails agency = AgencyDetails.getInstance();
      //  agency.addReview(r1);

        boolean expResult = true;
      //  boolean result = AgencyDetails.getInstance().deleteReviewById(reviewID);
       // assertEquals(expResult, result);
    }
}