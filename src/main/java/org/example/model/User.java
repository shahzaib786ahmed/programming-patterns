package org.example.model;

public abstract class User {
    private String userId;
    private String lName;
    private String fName;
    private String passportNum;
    private String phoneNumber;
    private String emailAddress;
    private int age;

    public User(String userId, String lName, String fName, String passportNum, String phoneNumber, String emailAddress, int age) {
        this.userId = userId;
        this.lName = lName;
        this.fName = fName;
        this.passportNum = passportNum;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.age = age;
    }

    private static String generateRandomId() {
        return null;
    }

    public boolean isPassportNumValid(String passportNum) {
        return false;
    }
    public boolean isPhoneNumberValid(String phoneNumber) {
        return false;
    }

    public boolean isEmailValid() {
        return false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        this.passportNum = passportNum;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract void displayDetails();
}
