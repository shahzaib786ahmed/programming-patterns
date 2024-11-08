package org.example.model;

import java.util.Random;

public abstract class User {
    private int userId;
    private String lName;
    private String fName;
    private String passportNum;
    private String phoneNumber;
    private String emailAddress;
    private int age;

    public User(String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age) {
        this.userId = generateRandomId();
        this.lName = lName;
        this.fName = fName;
        this.passportNum = validatePassportNum(passportNum);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.emailAddress = validateEmailAddress(emailAddress);
        this.age = age;
    }

    private static int generateRandomId() {
        Random random = new Random();
        int randomId;

        randomId = 1 + random.nextInt(999999);
        return randomId;
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
            throw new IllegalArgumentException("Passport Number contains 9 characters");
        }
    }
    public boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            return true;
        } else {
            throw new IllegalArgumentException("Phone Number contains 10 characters");
        }
    }

    public boolean isEmailValid(String emailAddress) {
        return emailAddress.contains("@");
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        generateRandomId();
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        validatePassportNum(passportNum);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        validateEmailAddress(emailAddress);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract void displayDetails();
}
