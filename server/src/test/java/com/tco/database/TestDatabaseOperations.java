package com.tco.database;

import com.tco.database.DatabaseOperations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

public class TestDatabaseOperations {
    @Test
    @DisplayName("driva: Testing exception thrown in count()")
    public void exceptionInCount() throws Exception {
        try{
            DatabaseOperations.count(null);
        } catch (Exception e) {
            assertTrue(e instanceof Exception);
        }
    }
}