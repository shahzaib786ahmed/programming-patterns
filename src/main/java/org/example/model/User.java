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
     * Validates the provided passport number and assigns it to the current instance if valid.
     *
     * @param passportNum the passport number to validate.
     * @return the validated passport number.
     * @throws IllegalArgumentException if the passport number is invalid.
     */
    public String validatePassportNum(String passportNum) {
        if (isPassportNumValid(passportNum)) {
            this.passportNum = passportNum;
            return passportNum;
        } else {
            throw new IllegalArgumentException("Invalid passport number!");
        }
    }

    /**
     * Validates the provided phone number and assigns it to the current instance if valid.
     *
     * @param phoneNumber the phone number to validate.
     * @return the validated phone number.
     * @throws IllegalArgumentException if the phone number is invalid.
     */
    public String validatePhoneNumber(String phoneNumber) {
        if (isPhoneNumberValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
            return phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number!");
        }
    }

    /**
     * Validates the provided email address and assigns it to the current instance if valid.
     *
     * @param emailAddress the email address to validate.
     * @return the validated email address.
     * @throws IllegalArgumentException if the email address is invalid.
     */
    public String validateEmailAddress(String emailAddress) {
        if (isEmailValid(emailAddress)) {
            this.emailAddress = emailAddress;
            return emailAddress;
        } else {
            throw new IllegalArgumentException("Invalid email address!");
        }
    }

    /**
     * Checks if the provided passport number is valid.
     * A valid passport number is non-null and consists of exactly 9 alphanumeric characters.
     *
     * @param passportNum the passport number to check.
     * @return {@code true} if the passport number is valid, {@code false} otherwise.
     */
    public boolean isPassportNumValid(String passportNum) {
        return passportNum != null && passportNum.matches("[A-Za-z0-9]{9}");
    }

    /**
     * Checks if the provided phone number is valid.
     * A valid phone number must contain exactly 10 characters.
     *
     * @param phoneNumber the phone number to check.
     * @return {@code true} if the phone number is valid, {@code false} otherwise.
     * @throws IllegalArgumentException if the phone number does not have exactly 10 characters.
     */
    public boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            return true;
        } else {
            throw new IllegalArgumentException("Phone Number should contain 10 characters");
        }
    }

    /**
     * Checks if the provided email address is valid.
     * A valid email address must contain the '@' symbol.
     *
     * @param emailAddress the email address to check.
     * @return {@code true} if the email address is valid, {@code false} otherwise.
     * @throws IllegalArgumentException if the email address does not contain the '@' symbol.
     */
    public boolean isEmailValid(String emailAddress) {
        if (emailAddress.contains("@")) {
            return true;
        } else {
            throw new IllegalArgumentException("Email address is invalid! Should include '@'");
        }
    }

    public abstract void displayDetails();
}
