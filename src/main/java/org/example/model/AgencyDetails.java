package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class AgencyDetails {
    private static AgencyDetails instance;
    private String agencyName;
    private String agencyAddress;
    private String agencyPhone;
    private String agencyEmail;
    private LinkedList<Review> customerReviews;

    private AgencyDetails() {
        this.agencyName = "";
        this.agencyAddress = "";
        this.agencyPhone = "";
        this.agencyEmail = "";
        this.customerReviews = new LinkedList<>();
    }
//TODO:ADD AND REMOVE SHOULD BE IN CONTROLLER
    /**
     * Adds a review of the customer to the list of customer reviews
     * @param review to be added from the client
     */
    public void addReview(Review review) {
        if (review != null) {
            customerReviews.add(review);
            System.out.println("Review added successfully!");
        } else {
            System.out.println("Invalid review. Cannot add null review.");
        }
    }

    /**
     * Deletes a review of the customer
     * @param reviewId of the review to be deleted
     * @return the boolean to see if the review is found by reviewId, if it is, delete it
     */
    public boolean deleteReviewById(String reviewId) {
        for (Review review : customerReviews) {
            if (review.getReviewId().equals(reviewId)) {
                customerReviews.remove(review);
                System.out.println("Review removed successfully.");
                return true;
            }
        }
        System.out.println("Review not found with the provided ID.");
        return false;
    }

    /**
     * Checks if email address format is valid
     * @param email to be checked to see if it's valid
     * @return the boolean that checks if it contains @ and .com
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".com");
    }

    /**
     * Checks if phone number format is valid
     * @param phone to be checked to see if it's valid
     * @return the boolean that checks if it contains 10 digits and not more or less
     */
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    /**
     * Sets an instance of agencyDetails
     * @param instance of a single agencyDetail
     */
    public static void setInstance(AgencyDetails instance) {
        AgencyDetails.instance = instance;
    }

    /**
     * Sets the name of the agency
     * @param agencyName of the agency
     */
    public void setAgencyName(String agencyName) {
        if (agencyName != null && !agencyName.trim().isEmpty()) {
            this.agencyName = agencyName;
        } else {
            throw new IllegalArgumentException("Agency name cannot be null or empty.");
        }
    }

    /**
     * Sets the phone number of the agency
     * @param agencyPhone of the agency
     */
    public void setAgencyPhone(String agencyPhone) {
        if (isValidPhone(agencyPhone)) {
            this.agencyPhone = agencyPhone;
        } else {
            throw new IllegalArgumentException("Invalid phone number: must contain exactly 10 digits.");
        }
    }

    /**
     * Sets the email address of the agency
     * @param agencyEmail of the agency
     */
    public void setAgencyEmail(String agencyEmail) {
        if (isValidEmail(agencyEmail)) {
            this.agencyEmail = agencyEmail;
        } else {
            throw new IllegalArgumentException("Invalid email: must contain '@'.");
        }
    }

    /**
     * If an instance of AgencyDetails is not created, it's created
     * @return using singleton, to see if there's one instance created only, if not, create one
     */
    public static AgencyDetails getInstance() {
        if (instance == null) {
            synchronized (AgencyDetails.class) {
                if (instance == null) {
                    instance = new AgencyDetails();
                }
            }
        }
        return instance;
    }

    /**
     * Displays the details of the agency
     */
    public void displayAgencyDetails() {
        System.out.println("===================================");
        System.out.println("         AGENCY DETAILS           ");
        System.out.println("===================================");
        System.out.println("Name      : " + agencyName);
        System.out.println("Address   : " + agencyAddress);
        System.out.println("Phone     : " + agencyPhone);
        System.out.println("Email     : " + agencyEmail);
        System.out.println("-----------------------------------");
        System.out.print("Customer Reviews:");
        if (customerReviews.isEmpty()) {
            System.out.println("  No reviews available.");
        } else {
            for (int i = 0; i < customerReviews.size(); i++) {
                System.out.println(customerReviews.get(i));
            }
        }
        System.out.println("===================================");
    }
}
