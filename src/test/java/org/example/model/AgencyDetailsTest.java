package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the AgencyDetails class. These tests cover the validation logic
 * for agency name, phone number, and email address.
 */
class AgencyDetailsTest {

    /**
     * Test the validation of a valid agency name.
     */
    @Test
    void testValidateAgencyName_ValidName() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String validName = "SkibidiAgency";
        String result = agencyDetails.validateAgencyName(validName);
        assertEquals(validName, result);
    }

    /**
     * Test the validation when the agency name is null.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateAgencyName_NullName() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyName(null);
        });
        assertEquals("Agency name cannot be null or empty.", exception.getMessage());
    }

    /**
     * Test the validation when the agency name is an empty string.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateAgencyName_EmptyName() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyName("");
        });
        assertEquals("Agency name cannot be null or empty.", exception.getMessage());
    }

    /**
     * Test the validation when the agency name contains only whitespace.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateAgencyName_WhitespaceName() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyName("   ");
        });
        assertEquals("Agency name cannot be null or empty.", exception.getMessage());
    }

    /**
     * Test the validation of a valid agency phone number.
     */
    @Test
    void testValidateAgencyPhone_ValidPhone() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String validPhone = "1234567890";
        String result = agencyDetails.validateAgencyPhone(validPhone);

        assertEquals(validPhone, result, "The validated phone number should match the input.");
    }

    /**
     * Test the validation when the agency phone number is too short.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateAgencyPhone_InvalidPhone_ShortNumber() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String invalidPhone = "12345";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyPhone(invalidPhone);
        });

        assertEquals("Invalid phone number: must contain exactly 10 digits.", exception.getMessage());
    }

    /**
     * Test the validation when the agency phone number is too long.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateAgencyPhone_InvalidPhone_LongNumber() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String invalidPhone = "123456789012";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyPhone(invalidPhone);
        });

        assertEquals("Invalid phone number: must contain exactly 10 digits.", exception.getMessage());
    }

    /**
     * Test the validation when the agency phone number contains non-numeric characters.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateAgencyPhone_InvalidPhone_NonNumeric() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String invalidPhone = "12345abcde";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyPhone(invalidPhone);
        });

        assertEquals("Invalid phone number: must contain exactly 10 digits.", exception.getMessage());
    }

    /**
     * Test the validation when the agency phone number is null.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateAgencyPhone_NullPhone() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyPhone(null);
        });

        assertEquals("Invalid phone number: must contain exactly 10 digits.", exception.getMessage());
    }

    /**
     * Test the validation when the agency phone number is an empty string.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateAgencyPhone_InvalidPhone_EmptyString() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String invalidPhone = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyPhone(invalidPhone);
        });

        assertEquals("Invalid phone number: must contain exactly 10 digits.", exception.getMessage());
    }

    /**
     * Test the validation when the agency phone number contains only whitespace.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateAgencyPhone_InvalidPhone_Whitespace() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String invalidPhone = "   ";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyPhone(invalidPhone);
        });

        assertEquals("Invalid phone number: must contain exactly 10 digits.", exception.getMessage());
    }

    /**
     * Test the validation of a valid email address.
     */
    @Test
    void testValidateEmailAddress_ValidEmail() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String validEmail = "test@example.com";
        String result = agencyDetails.validateAgencyEmail(validEmail);

        assertEquals(validEmail, result, "The validated email address should match the input.");
    }

    /**
     * Test the validation when the agency email is missing the '@' symbol.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateEmailAddress_InvalidEmail_MissingAtSymbol() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String invalidEmail = "testexample.com";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyEmail(invalidEmail);
        });

        assertEquals("Invalid email: must contain '@'.", exception.getMessage());
    }

    /**
     * Test the validation when the agency email is null.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateEmailAddress_InvalidEmail_Null() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyEmail(null);
        });

        assertEquals("Invalid email: must contain '@'.", exception.getMessage());
    }

    /**
     * Test the validation when the agency email is an empty string.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateEmailAddress_InvalidEmail_EmptyString() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String invalidEmail = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyEmail(invalidEmail);
        });

        assertEquals("Invalid email: must contain '@'.", exception.getMessage());
    }

    /**
     * Test the validation when the agency email contains only whitespace.
     * Expects an IllegalArgumentException with a specific message.
     */
    @Test
    void testValidateEmailAddress_InvalidEmail_Whitespace() {
        AgencyDetails agencyDetails = AgencyDetails.getInstance();
        String invalidEmail = "   ";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agencyDetails.validateAgencyEmail(invalidEmail);
        });

        assertEquals("Invalid email: must contain '@'.", exception.getMessage());
    }
}
