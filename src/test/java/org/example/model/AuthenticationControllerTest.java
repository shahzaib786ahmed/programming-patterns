package org.example.model;

import org.example.controller.AuthentificationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the AuthentificationController class.
 */
public class AuthenticationControllerTest {
    private CompanySystem companySystem;
    private AuthentificationController authentificationController;

    /**
     * Setup method to initialize the company system and authentication controller.
     * Creates a list of clients and sets it in the company system before each test.
     */
    @BeforeEach
    public void setUp() {
        companySystem = CompanySystem.getInstance();
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Ahmed", "Shahzaib", "A4325M678", "5533257543", "shahzaibahmed@gmail.com", 20, "ShahzaibisAStar", "Shahzaib123", 0));
        clients.add(new Client("Kharchafi", "Aya", "B6325I226", "4423277543", "ayakharchafi@gmail.com", 20, "ayak", "ayak2", 0));
        companySystem.setClients(clients);

        authentificationController = new AuthentificationController();
    }

    /**
     * Test the login functionality with null username and password.
     * Should return false because both username and password are null.
     */
    @Test
    public void testIsLogin_NullUserNameAndPassword() {
        assertFalse(authentificationController.isLogin(null, null));
    }

    /**
     * Test the login functionality with a null username and a valid password.
     * Should return false because the username is null.
     */
    @Test
    public void testIsLogin_NullUserName() {
        assertFalse(authentificationController.isLogin(null, "ayak2"));
    }

    /**
     * Test the login functionality with a valid username and a null password.
     * Should return false because the password is null.
     */
    @Test
    public void testIsLogin_NullPassword() {
        assertFalse(authentificationController.isLogin("ayak", null));
    }

    /**
     * Test the login functionality with valid username and password.
     * Should return true if the credentials match a valid client.
     */
    @Test
    public void testIsLogin_ValidCredentials() {
        assertTrue(authentificationController.isLogin("ShahzaibisAStar", "Shahzaib123"));
    }

    /**
     * Test the login functionality with invalid credentials.
     * Should return false if the username or password do not match a valid client.
     */
    @Test
    public void testIsLogin_InvalidCredentials() {
        assertFalse(authentificationController.isLogin("ShahzaibisAStar", "rdgfxgxfg"));
        assertFalse(authentificationController.isLogin("gsdxgvxx", "Shahzaib123"));
    }
}
