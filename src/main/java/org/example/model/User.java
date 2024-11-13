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

    private static int generateRandomId() {
        Random random = new Random();
        return 1 + random.nextInt(999999);

    }

    private String validatePassportNum(String passportNum) {
        if (isPassportNumValid(passportNum)) {
            return passportNum;
        } else {
            throw new IllegalArgumentException("Invalid passport number!");
        }
    }

    private String validatePhoneNumber(String phoneNumber) {
        if (isPhoneNumberValid(phoneNumber)) {
            return phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number!");
        }
    }

    private String validateEmailAddress(String emailAddress) {
        if (isEmailValid(emailAddress)) {
            return emailAddress;
        } else {
            throw new IllegalArgumentException("Invalid email address!");
        }
    }

    public boolean isPassportNumValid(String passportNum) {
        if (passportNum.length() == 9) {
            return true;
        } else {
            throw new IllegalArgumentException("Passport Number should contain 9 characters");
        }
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            return true;
        } else {
            throw new IllegalArgumentException("Phone Number should contain 10 characters");
        }
    }

    public boolean isEmailValid(String emailAddress) {
        if (emailAddress.contains("@") && emailAddress.contains(".com")) {
            return true;
        } else {
            throw new IllegalArgumentException("Email address is invalid! Should include '@' or/and '.com'");
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


    public abstract void displayDetails();


}
