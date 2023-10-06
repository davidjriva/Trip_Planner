package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlace{
    @Test
    @DisplayName("davematt: test origin Place")
    public void testOrigin() {
        Place place = new Place("0", "0");
        assertEquals(0.0, place.latRadians());
        assertEquals(0.0, place.lonRadians());
    }

    @Test
    @DisplayName("davematt: test dateline postive")
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

    @Test
    @DisplayName("bachrock: test north pole")
    public void testNorthPole() {
        Place place = new Place("90", "0");
        assertEquals(PI/2, place.latRadians());
        assertEquals(0.0, place.lonRadians());
    }
        
    @Test
    @DisplayName("driva: test dateline positive 1/2")
    public void testDatelineOneHalfPositive() {
        Place place = new Place("0", "90");
        assertEquals(0.0, place.latRadians());
        assertEquals(PI/2, place.lonRadians());
    }

    @Test
    @DisplayName("driva: test dateline negative 1/2")
    public void testDatelineOneHalfNegative() {
        Place place = new Place("0", "-90");
        assertEquals(0.0, place.latRadians());
        assertEquals(-PI/2, place.lonRadians());
    }

    @Test
    @DisplayName("driva: testing ID feature of Place")
    public void testID(){
        Place place = new Place();
        place.put("id", "123");
        assertEquals("123", place.id());
    }

    @Test
    @DisplayName("driva: testing default ctor")
    public void testDefaultConstructor() {
        Place place = new Place();
        
        assertNull(place.get("latitude"));
        assertNull(place.get("longitude"));
        assertNull(place.id());
    }

    @Test
    @DisplayName("driva: testing ctor with two arguments")
    public void testConstructor() {
        Place place = new Place("40.7128", "-74.0060");
        assertEquals("40.7128", place.get("latitude"));
        assertEquals("-74.0060", place.get("longitude"));
    }

    @Test
    @DisplayName("driva: testing getting the lat value in radians for a Place")
    public void testLatRadians() {
        Place place = new Place("40.7128", "-74.0060");
        assertEquals(Math.toRadians(40.7128), place.latRadians(), 0.0001);
    }

    @Test
    @DisplayName("driva: testing getting the long value in radians for a Place")
    public void testLonRadians() {
        Place place = new Place("40.7128", "-74.0060");
        assertEquals(Math.toRadians(-74.0060), place.lonRadians(), 0.0001);
    }

    @Test
    @DisplayName("bachrock: testing id() with ctor and two arguments")
    public void testIdWithCtorAndTwoArgs() {
        Place place = new Place("61.218", "-149.900");
        place.put("id", "Anchorage");
        assertEquals("Anchorage", place.id());
    }

    @Test
    @DisplayName("bachrock: testing isempty() for place")
    public void isPlaceEmpty() {
        Place place = new Place();
        assertEquals(true, place.isEmpty());
    }
}
