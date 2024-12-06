package org.example.model;

import org.example.controller.AuthentificationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationControllerTest {
    private CompanySystem companySystem;
    private AuthentificationController authentificationController;

    @BeforeEach
    public void setUp() {
        companySystem = CompanySystem.getInstance();
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Ahmed","Shahzaib","A4325M678","5533257543","shahzaibahmed@gmail.com",20,"ShahzaibisAStar","Shahzaib123", 0));
        clients.add(new Client("Kharchafi","Aya","B6325I226","4423277543","ayakharchafi@gmail.com",20,"ayak","ayak2", 0));
        companySystem.setClients(clients);

        authentificationController = new AuthentificationController();
    }

    @Test
    public void testIsLogin_NullUserNameAndPassword() {
        assertFalse(authentificationController.isLogin(null, null));
    }

    @Test
    public void testIsLogin_NullUserName() {
        assertFalse(authentificationController.isLogin(null, "ayak2"));
    }

    @Test
    public void testIsLogin_NullPassword() {
        assertFalse(authentificationController.isLogin("ayak", null));
    }

    @Test
    public void testIsLogin_ValidCredentials() {
        assertTrue(authentificationController.isLogin("ShahzaibisAStar", "Shahzaib123"));
    }

    @Test
    public void testIsLogin_InvalidCredentials() {
        assertFalse(authentificationController.isLogin("ShahzaibisAStar", "rdgfxgxfg"));
        assertFalse(authentificationController.isLogin("gsdxgvxx", "Shahzaib123"));
    }
}
