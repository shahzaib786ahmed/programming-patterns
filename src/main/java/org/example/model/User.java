package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String username;
    private String password;
    private int age;

    private static int nextId = 1;

    public User(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age, String username, String password) {
        this.id = nextId++;
        this.lName = lName;
        this.fName = fName;
        this.passportNum = validatePassportNum(passportNum);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.emailAddress = validateEmailAddress(emailAddress);
        this.age = age;
        this.username = username;
        this.password = password;
    }

    /**
     * Validates the format of the passport number
     *
     * @param passportNum to be used to verify the format
     * @return the string to return the passport number or error message
     */
    private String validatePassportNum(String passportNum) {
        if (isPassportNumValid(passportNum)) {
            return passportNum;
        }
        throw new IllegalArgumentException("Invalid passport number!");
    }

    /**
     * Validates the format of the phone number
     *
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
     *
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
     *
     * @param passportNum to be used to verify the format
     * @return the boolean to see if it has the correct format, if not, throw an exception
     */
    public boolean isPassportNumValid(String passportNum) {
        return passportNum != null && passportNum.matches("[A-Za-z0-9]{9}");
    }

    /**
     * Checks if the phone number has the proper format
     *
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
     *
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

    /**
     * Sets the passport number
     *
     * @param passportNum of the passport
     */
    public void setPassportNum(String passportNum) {
        validatePassportNum(passportNum);
    }

    /**
     * Sets the phone number
     *
     * @param phoneNumber of the passenger
     */
    public void setPhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
    }

    /**
     * Sets the email address
     *
     * @param emailAddress of the passenger
     */
    public void setEmailAddress(String emailAddress) {
        validateEmailAddress(emailAddress);
    }

    /**
     * Displays the details of the user
     */
    public abstract void displayDetails();
}
