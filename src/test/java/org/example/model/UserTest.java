package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    //TODO:ADD MORE TESTS
    @Test
    void isPassportNumValid() {
        User user = new Client("Ahmed", "Shahzaib","A53145631","5533257543","shahzaib@gmail.com",20,"ShahzaibisAStar","Shahzaib123");
        String passportNum = "B34984281";
        boolean expResult = true;
        boolean result = user.isPassportNumValid(passportNum);
        assertEquals(expResult, result);
    }

    @Test
    void isPhoneNumberValid() {
        User user = new Client("Ahmed", "Shahzaib","A53145631","5533257543","shahzaib@gmail.com",20,"ShahzaibisAStar","Shahzaib123");
        String phoneNumber = "5533257543";
        boolean expResult = true;
        boolean result = user.isPhoneNumberValid(phoneNumber);
        assertEquals(expResult, result);
    }

    @Test
    void isEmailValid() {
        User user = new Client("Ahmed", "Shahzaib","A53145631","5533257543","shahzaib@gmail.com",20,"ShahzaibisAStar","Shahzaib123");
        String email = "shahzaib@gmail.com";
        boolean expResult = true;
        boolean result = user.isEmailValid("shahzaib@gmail.com");
        assertEquals(expResult, result);
    }
}