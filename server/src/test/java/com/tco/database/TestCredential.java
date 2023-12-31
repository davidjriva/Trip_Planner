package com.tco.database;

import com.tco.database.Credential;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TestCredential {
    @Test
    @DisplayName("driva: Testing credential returns the correct URL for SSH tunnel")
    public void testUrlWithSSHTunnel() {
        System.setProperty("CS314_USE_DATABASE_TUNNEL", "true");

        assertTrue("jdbc:mariadb://127.0.0.1:56247/cs314".equals(Credential.url()) || 
        "jdbc:mariadb://faure.cs.colostate.edu/cs314".equals(Credential.url()) ||
        "jdbc:mariadb://127.0.0.1:3306/cs314".equals(Credential.url()));
    }
}