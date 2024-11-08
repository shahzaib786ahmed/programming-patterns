package org.example.model;

import java.util.Objects;
import java.util.Random;

public abstract class User {
    private int userId;
    private String lName;
    private String fName;
    private String passportNum;
    private String phoneNumber;
    private String emailAddress;
    private int age;

    public User() {
        this.userId = generateRandomId();
        this.lName = "";
        this.fName = "";
        this.passportNum = validatePassportNum("");
        this.phoneNumber = validatePhoneNumber("");
        this.emailAddress = validateEmailAddress("");
        this.age = 0;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && age == user.age && Objects.equals(lName, user.lName) && Objects.equals(fName, user.fName) && Objects.equals(passportNum, user.passportNum) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(emailAddress, user.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, lName, fName, passportNum, phoneNumber, emailAddress, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", lName='" + lName + '\'' +
                ", fName='" + fName + '\'' +
                ", passportNum='" + passportNum + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", age=" + age +
                '}';
    }
}
