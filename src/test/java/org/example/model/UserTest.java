package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the User class, specifically testing validation methods
 * for passport numbers, phone numbers, and email addresses for both Employee and Client users.
 */
class UserTest {

    /**
     * Test the validation of a valid passport number for an Employee user.
     * Should return the same passport number if it is valid.
     */
    @Test
    void testValidatePassportNum_ValidPassport_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        String expected = "AB1234567";
        String result = employee.validatePassportNum(expected);

        assertEquals(expected, result);
    }

    /**
     * Test the validation of an invalid passport number for an Employee user.
     * Should throw an IllegalArgumentException with the message "Invalid passport number!".
     */
    @Test
    void testValidatePassportNum_InvalidPassport_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employee.validatePassportNum("1234");
        });

        assertEquals("Invalid passport number!", exception.getMessage());
    }

    /**
     * Test the validation of a valid phone number for an Employee user.
     * Should return the same phone number if it is valid.
     */
    @Test
    void testValidatePhoneNumber_ValidPhone_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        String expected = "4382296553";
        String result = employee.validatePhoneNumber(expected);

        assertEquals(expected, result);
    }

    /**
     * Test the validation of an invalid phone number for an Employee user.
     * Should throw an IllegalArgumentException with the message "Phone Number should contain 10 characters".
     */
    @Test
    void testValidatePhoneNumber_InvalidPhone_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employee.validatePhoneNumber("43822965");
        });

        assertEquals("Phone Number should contain 10 characters", exception.getMessage());
    }

    /**
     * Test the validation of a valid email address for an Employee user.
     * Should return the same email address if it is valid.
     */
    @Test
    void testValidateEmailAddress_ValidEmail_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        String expected = "shahzaibahmed@gmail.com";
        String result = employee.validateEmailAddress(expected);

        assertEquals(expected, result);
    }

    /**
     * Test the validation of an invalid email address for an Employee user.
     * Should throw an IllegalArgumentException with the message "Email address is invalid! Should include '@'".
     */
    @Test
    void testValidateEmailAddress_InvalidEmail_Employee() {
        User employee = new Employee("Ahmed", "Shahzaib", "AB1234567", "4382296553", "shahzaibahmed@gmail.com", 20, "shahzaiba", "shah786", 50);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employee.validateEmailAddress("ayakharchafi.com");
        });

        assertEquals("Email address is invalid! Should include '@'", exception.getMessage());
    }

    /**
     * Test the validation of a valid passport number for a Client user.
     * Should return the same passport number if it is valid.
     */
    @Test
    void testValidatePassportNum_ValidPassport_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);
        String expected = "V234GJ765";
        String result = client.validatePassportNum(expected);

        assertEquals(expected, result);
    }

    /**
     * Test the validation of an invalid passport number for a Client user.
     * Should throw an IllegalArgumentException with the message "Invalid passport number!".
     */
    @Test
    void testValidatePassportNum_InvalidPassport_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            client.validatePassportNum("1234");  // Invalid passport number
        });

        assertEquals("Invalid passport number!", exception.getMessage());
    }

    /**
     * Test the validation of a valid phone number for a Client user.
     * Should return the same phone number if it is valid.
     */
    @Test
    void testValidatePhoneNumber_ValidPhone_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);
        String expected = "1753245784";
        String result = client.validatePhoneNumber(expected);

        assertEquals(expected, result);
    }

    /**
     * Test the validation of an invalid phone number for a Client user.
     * Should throw an IllegalArgumentException with the message "Phone Number should contain 10 characters".
     */
    @Test
    void testValidatePhoneNumber_InvalidPhone_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            client.validatePhoneNumber("43822965");  // Invalid phone number
        });

        assertEquals("Phone Number should contain 10 characters", exception.getMessage());
    }

    /**
     * Test the validation of a valid email address for a Client user.
     * Should return the same email address if it is valid.
     */
    @Test
    void testValidateEmailAddress_ValidEmail_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);
        String expected = client.getEmailAddress();
        String result = client.validateEmailAddress(expected);

        assertEquals(expected, result);
    }

    /**
     * Test the validation of an invalid email address for a Client user.
     * Should throw an IllegalArgumentException with the message "Email address is invalid! Should include '@'".
     */
    @Test
    void testValidateEmailAddress_InvalidEmail_Client() {
        User client = new Client("Ali", "Mohammed", "V234GJ765", "1753245784", "mohammedAli@example.com", 30, "moAli", "mo786", 0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            client.validateEmailAddress("mohammedAliexample.com");  // Invalid email address
        });

        assertEquals("Email address is invalid! Should include '@'", exception.getMessage());
    }
}
