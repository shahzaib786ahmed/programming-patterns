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

    public String validatePassportNum(String passportNum) {
        if (isPassportNumValid(passportNum)) {
            this.passportNum = passportNum;
            return passportNum;
        } else {
            throw new IllegalArgumentException("Invalid passport number!");
        }
    }

    public String validatePhoneNumber(String phoneNumber) {
        if (isPhoneNumberValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
            return phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number!");
        }
    }

    public String validateEmailAddress(String emailAddress) {
        if (isEmailValid(emailAddress)) {
            this.emailAddress = emailAddress;
            return emailAddress;
        } else {
            throw new IllegalArgumentException("Invalid email address!");
        }
    }

    public boolean isPassportNumValid(String passportNum) {
        return passportNum != null && passportNum.matches("[A-Za-z0-9]{9}");
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            return true;
        } else {
            throw new IllegalArgumentException("Phone Number should contain 10 characters");
        }
    }

    public boolean isEmailValid(String emailAddress) {
        if (emailAddress.contains("@")) {
            return true;
        } else {
            throw new IllegalArgumentException("Email address is invalid! Should include '@'");
        }
    }

    public abstract void displayDetails();
}
