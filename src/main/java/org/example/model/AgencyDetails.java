package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.DatabaseController;

import java.util.List;

@Getter
@Setter
public class AgencyDetails {
    private static AgencyDetails instance;
    private String agencyName;
    private String agencyAddress;
    private String agencyPhone;
    private String agencyEmail;
    private List<Review> customerReviews;

    private AgencyDetails() {
        this.agencyName = "";
        this.agencyAddress = "";
        this.agencyPhone = "";
        this.agencyEmail = "";
        this.customerReviews = DatabaseController.queryAllReviews();
    }

    /**
     * Checks if email address format is valid
     *
     * @param email to be checked to see if it's valid
     * @return the boolean that checks if it contains @ and .com
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".com");
    }

    /**
     * Checks if phone number format is valid
     *
     * @param phone to be checked to see if it's valid
     * @return the boolean that checks if it contains 10 digits and not more or less
     */
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    /**
     * Sets the name of the agency
     *
     * @param agencyName of the agency
     */
    public String validateAgencyName(String agencyName) {
        if (agencyName != null && !agencyName.trim().isEmpty()) {
            this.agencyName = agencyName;
            return agencyName;
        } else {
            throw new IllegalArgumentException("Agency name cannot be null or empty.");
        }
    }

    /**
     * Sets the phone number of the agency
     *
     * @param agencyPhone of the agency
     */
    public String validateAgencyPhone(String agencyPhone) {
        if (agencyPhone == null || !isValidPhone(agencyPhone)) {
            throw new IllegalArgumentException("Invalid phone number: must contain exactly 10 digits.");
        }

        this.agencyPhone = agencyPhone;
        return agencyPhone;
    }

    /**
     * Sets the email address of the agency
     *
     * @param agencyEmail of the agency
     */
    public String validateAgencyEmail(String agencyEmail) {
        if (agencyEmail == null || !isValidEmail(agencyEmail)) {
            throw new IllegalArgumentException("Invalid email: must contain '@'.");
        }

        this.agencyEmail = agencyEmail;
        return agencyEmail;
    }

    /**
     * If an instance of AgencyDetails is not created, it's created
     *
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
