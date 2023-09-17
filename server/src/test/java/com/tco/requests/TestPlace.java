package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlace{
    @Test
    @DisplayName("dmatt: test origin Place")
    public void testOrigin() {
        Place place = new Place("0", "0");
        assertEquals(0.0, place.latRadians());
        assertEquals(0.0, place.lonRadians());
    }

    @Test
    @DisplayName("dmatt: test dateline postive")
    public void testDatelinePositive() {
        Place place = new Place("0", "180");
        assertEquals(0.0, place.latRadians());
        assertEquals(PI, place.lonRadians());
    }

    @Test
    @DisplayName("bachrock: test dateline negative")
    public void testDatelineNegative() {
        Place place = new Place("0", "-180");
        assertEquals(0.0, place.latRadians());
        assertEquals(-PI, place.lonRadians());
    }

    Test
    @DisplayName("driva: test dateline positive 1/2")
    public void testDatelineOneHalfPositive() {
        Place place = new Place("0", "90");
        assertEquals(0.0, place.latRadians());
        assertEquals(PI/2, place.lonRadians());
    }

    Test
    @DisplayName("driva: test dateline negative 1/2")
    public void testDatelineOneHalfNegative() {
        Place place = new Place("0", "-90");
        assertEquals(0.0, place.latRadians());
        assertEquals(-PI/2, place.lonRadians());
    }
}
