package com.tco.database;

import com.tco.database.Select;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TestSelect {
    @Test
    @DisplayName("driva: checkLimit with valid limit")
    public void testValidLimit() {
        assertEquals(Select.checkLimit(50), 50);
        assertEquals(Select.checkLimit(99), 99);
        assertEquals(Select.checkLimit(1), 1);
    }

    @Test
    @DisplayName("driva: checkLimit with invalid limit")
    public void testInvalidLimit() {
        assertEquals(Select.checkLimit(0), 100);
        assertEquals(Select.checkLimit(100), 100);
        assertEquals(Select.checkLimit(-50), 100);
        assertEquals(Select.checkLimit(1000), 100);
        assertEquals(Select.checkLimit(Integer.MAX_VALUE), 100);
        assertEquals(Select.checkLimit(Integer.MIN_VALUE), 100);
    }
}