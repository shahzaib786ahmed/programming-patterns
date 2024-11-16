package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.Random;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public abstract class User {
    private int id;
    private String lName;
    private String fName;
    private String passportNum;
    private String phoneNumber;
    private String emailAddress;
    private int age;

    public User(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age) {
        this.id = generateRandomId();
        this.lName = lName;
        this.fName = fName;
        this.passportNum = validatePassportNum(passportNum);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.emailAddress = validateEmailAddress(emailAddress);
        this.age = age;
    }

    /**
     * Generates a random ID for the user
     * @return an int containing a random ID for the user
     */
    private static int generateRandomId() {
        Random random = new Random();
        return (1 + random.nextInt(999999));
    }

    /**
     * Validates the format of the passport number
     * @param passportNum to be used to verify the format
     * @return the string to return the passport number or error message
     */
    private String validatePassportNum(String passportNum) {
        if (isPassportNumValid(passportNum)) {
            return passportNum;
        } else {
            throw new IllegalArgumentException("Invalid passport number!");
        }
    }

    /**
     * Validates the format of the phone number
     * @param phoneNumber to be used to verify the format
     * @return the string to return the phone number or error message
     */
    private String validatePhoneNumber(String phoneNumber) {
        if (isPhoneNumberValid(phoneNumber)) {
            return phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number!");
        }
    }

    /**
     * Validates the format of the phone number
     * @param emailAddress to be used to verify the format
     * @return the string to return the email address or error message
     */
    private String validateEmailAddress(String emailAddress) {
        if (isEmailValid(emailAddress)) {
            return emailAddress;
        } else {
            throw new IllegalArgumentException("Invalid email address!");
        }
    }

    /**
     * Checks if the passport number has the proper format
     * @param passportNum to be used to verify the format
     * @return the boolean to see if it has the correct format, if not, throw an exception
     */
    public boolean isPassportNumValid(String passportNum) {
        if (passportNum.length() == 9) {
            return true;
        } else {
            throw new IllegalArgumentException("Passport Number should contain 9 characters");
        }
    }

    /**
     * Checks if the phone number has the proper format
     * @param phoneNumber to be used to verify the format
     * @return the boolean to see if it has the correct format, if not, throw an exception
     */
    public boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            return true;
        } else {
            throw new IllegalArgumentException("Phone Number should contain 10 characters");
        }
    }

    /**
     * Checks if the email address has the proper format
     * @param emailAddress to be used to verify the format
     * @return the boolean to see if it has the correct format, if not, throw an exception
     */
    public boolean isEmailValid(String emailAddress) {
        if (emailAddress.contains("@")) {
            return true;
        } else {
            throw new IllegalArgumentException("Email address is invalid! Should include '@'");
        }
    }

    public void setUserId(String userId) {
        generateRandomId();
    }

    public void setPassportNum(String passportNum) {
        validatePassportNum(passportNum);
    }

    public void setPhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
    }

    public void setEmailAddress(String emailAddress) {
        validateEmailAddress(emailAddress);
    }

    /**
     * Displays the details of the user
     */
    public abstract void displayDetails();
}
