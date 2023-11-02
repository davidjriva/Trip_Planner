package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;
import static com.tco.requests.Tour.initializeRemainingCities;

public class TestTour {
    @Test
    @DisplayName ("bachrock: tests initializeRemainingCities with array of one value")
    public void testInitializeRemainingCitiesOneValue() {
        boolean[] remainingCities = new boolean[1];
        initializeRemainingCities(remainingCities);

        assertTrue(remainingCities[0]);
    }

    @Test
    @DisplayName ("bachrock: tests initializeRemainingCities with false array of ten values")
    public void testInitializeRemainingCitiesTenValues() {
        boolean[] remainingCities = new boolean[10];
        for(boolean remainingCity : remainingCities) {
            remainingCity = false;
        }
        initializeRemainingCities(remainingCities);
        for(boolean remainingCity : remainingCities) {
            assertTrue(remainingCity);
        }
    }

    @Test
    @DisplayName ("bachrock: tests initializeRemainingCities with false array")
    public void testInitializeRemainingCitiesFalseArray() {
        boolean[] remainingCities = new boolean[3];
        remainingCities[0] = false;
        remainingCities[1] = false;
        remainingCities[2] = false;

        initializeRemainingCities(remainingCities);

        assertTrue(remainingCities[0]);
        assertTrue(remainingCities[1]);
        assertTrue(remainingCities[2]);
    }

    @Test
    @DisplayName ("bachrock: tests initializeRemainingCities with large array")
    public void testInitializeRemainingCitiesLargeArray() {
        boolean[] remainingCities = new boolean[1000000];
        initializeRemainingCities(remainingCities);

        for(boolean remainingCity : remainingCities) {
            assertTrue(remainingCity);
        }
    }
}
