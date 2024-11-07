package org.example.model;

import java.util.LinkedList;

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
    public void addReview(Review review) {
        if (review != null) {
            customerReviews.add(review);
            System.out.println("Review added successfully!");
        } else {
            System.out.println("Invalid review. Cannot add null review.");
        }
    }
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

    private boolean isValidEmail(String email)
    {
        // Check if email contains an '@' symbol
        return email.contains("@");
    }
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}"); // Checks if phone contains exactly 10 digits
    }

    public static void setInstance(AgencyDetails instance) {
        AgencyDetails.instance = instance;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

    public String getAgencyPhone() {
        return agencyPhone;
    }

    public void setAgencyPhone(String agencyPhone) {
        if (isValidPhone(agencyPhone)) {
            this.agencyPhone = agencyPhone;
        } else {
            throw new IllegalArgumentException("Invalid phone number: must contain exactly 10 digits.");
        }
    }

    public String getAgencyEmail() {
        return agencyEmail;
    }

    public void setAgencyEmail(String agencyEmail) {
        if (isValidEmail(agencyEmail)) {
            this.agencyEmail = agencyEmail;
        } else {
            throw new IllegalArgumentException("Invalid email: must contain '@'.");
        }
    }

    public LinkedList<Review> getCustomerReviews() {
        return customerReviews;
    }

    public void setCustomerReviews(LinkedList<Review> customerReviews) {
        this.customerReviews = customerReviews;
    }

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

    public void displayAgencyDetails(){
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
