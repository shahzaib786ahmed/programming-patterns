package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void testValidatePassportNum_ValidPassport_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        String expected = "AB1234567";
        String result = employee.validatePassportNum(expected);

        assertEquals(expected, result);
    }

    @Test
    void testValidatePassportNum_InvalidPassport_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employee.validatePassportNum("1234");
        });

        assertEquals("Invalid passport number!", exception.getMessage());
    }

    @Test
    void testValidatePhoneNumber_ValidPhone_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        String expected = "4382296553";
        String result = employee.validatePhoneNumber(expected);

        assertEquals(expected, result);
    }

    @Test
    void testValidatePhoneNumber_InvalidPhone_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employee.validatePhoneNumber("43822965");
        });

        assertEquals("Phone Number should contain 10 characters", exception.getMessage());
    }

    @Test
    void testValidateEmailAddress_ValidEmail_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        String expected = "shahzaibahmed@gmail.com";
        String result = employee.validateEmailAddress(expected);

        assertEquals(expected, result);
    }

    @Test
    void testValidateEmailAddress_InvalidEmail_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employee.validateEmailAddress("ayakharchafi.com");
        });

        assertEquals("Email address is invalid! Should include '@'", exception.getMessage());
    }

    @Test
    void testValidatePassportNum_ValidPassport_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);
        String expected = "V234GJ765";
        String result = client.validatePassportNum(expected);

        assertEquals(expected, result);
    }

    @Test
    void testValidatePassportNum_InvalidPassport_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            client.validatePassportNum("1234");  // Invalid passport number
        });

        assertEquals("Invalid passport number!", exception.getMessage());
    }

    @Test
    void testValidatePhoneNumber_ValidPhone_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);
        String expected = "1753245784";
        String result = client.validatePhoneNumber(expected);

        assertEquals(expected, result);
    }

    @Test
    void testValidatePhoneNumber_InvalidPhone_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            client.validatePhoneNumber("43822965");  // Invalid phone number
        });

        assertEquals("Phone Number should contain 10 characters", exception.getMessage());
    }

    @Test
    void testValidateEmailAddress_ValidEmail_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);
        String expected = client.getEmailAddress();
        String result = client.validateEmailAddress(expected);

        assertEquals(expected, result);
    }

    @Test
    void testValidateEmailAddress_InvalidEmail_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            client.validateEmailAddress("mohammedAliexample.com");  // Invalid email address
        });

        assertEquals("Email address is invalid! Should include '@'", exception.getMessage());
    }
}