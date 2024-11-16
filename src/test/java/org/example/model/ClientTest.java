package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    //TODO:ADD MORE TESTS
    @Test
    void isLogin() {
        Client client = new Client("Ahmed", "Shahzaib","A53145631","5533257543","shahzaib@gmail.com",20,"ShahzaibisAStar","Aya<3");
        String userName = "ShahzaibisAStar";
        String password = "Aya<3";

        boolean expectResult = true;
        boolean result = client.isLogin(userName,password);
        assertEquals(expectResult, result);
    }
}