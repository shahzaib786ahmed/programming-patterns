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

    /**
     *
     * @param review
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
     *
     * @param reviewId
     * @return
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
     *
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".com");
    }

    /**
     *
     * @param phone
     * @return
     */
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}"); // Checks if phone contains exactly 10 digits
    }

    /**
     *
     * @param instance
     */
    public static void setInstance(AgencyDetails instance) {
        AgencyDetails.instance = instance;
    }

    /**
     *
     * @param agencyName
     */
    public void setAgencyName(String agencyName) {
        if (agencyName != null && !agencyName.trim().isEmpty()) {
            this.agencyName = agencyName;
        } else {
            throw new IllegalArgumentException("Agency name cannot be null or empty.");
        }
    }

    /**
     *
     * @param agencyPhone
     */
    public void setAgencyPhone(String agencyPhone) {
        if (isValidPhone(agencyPhone)) {
            this.agencyPhone = agencyPhone;
        } else {
            throw new IllegalArgumentException("Invalid phone number: must contain exactly 10 digits.");
        }
    }

    /**
     *
     * @param agencyEmail
     */
    public void setAgencyEmail(String agencyEmail) {
        if (isValidEmail(agencyEmail)) {
            this.agencyEmail = agencyEmail;
        } else {
            throw new IllegalArgumentException("Invalid email: must contain '@'.");
        }
    }

    /**
     *
     * @return
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
     *
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
