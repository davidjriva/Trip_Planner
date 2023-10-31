package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

public class TestTourConstruction{
    @Test
    @DisplayName("driva: test single place")
    public void testCreateDistanceMatrixSinglePlace() {
        Place[] places = {
            new Place("1.0", "2.0")
        };

        TourConstruction tourConstruction = new TourConstruction();
        tourConstruction.createDistanceMatrix(places);
        Long[][] distanceMatrix = tourConstruction.distanceMatrix;

        assertNotNull(distanceMatrix);
        assertEquals(places.length, distanceMatrix.length);
        assertEquals(0L, distanceMatrix[0][0].longValue());
    }

    @Test
    @DisplayName("driva: test 1")
    public void testCreateDistanceMatrixMultiplePlaces() {
        Place[] places = {
            new Place("1.0", "2.0"),
            new Place("3.0", "4.0"),
            new Place("5.0", "6.0")
        };

        TourConstruction tourConstruction = new TourConstruction();

        tourConstruction.createDistanceMatrix(places);
        Long[][] distanceMatrix = tourConstruction.distanceMatrix;

        assertNotNull(distanceMatrix);
        assertEquals(places.length, distanceMatrix.length);
        assertEquals(0L, distanceMatrix[0][0].longValue());
        assertEquals(314L, distanceMatrix[0][1].longValue());
        assertEquals(629L, distanceMatrix[0][2].longValue());
    }

    @Test
    @DisplayName("driva: test nn single place")
    public void testNearestNeighborSinglePlace() {
        Place[] places = {
            new Place("1.0", "2.0")
        };

        TourConstruction tc = new TourConstruction();
        tc.nearestNeighbor(places);
        int[] tourResults = tc.tourResults;
        
        assertNotNull(tourResults);
        assertEquals(places.length, tourResults.length);
        assertEquals(0, tourResults[0]);
    }

    @Test
    @DisplayName("driva: test nn multiple places")
    public void testNearestNeighborMultiplePlaces() {
        Place[] places = {
            new Place("1.0", "2.0"),
            new Place("3.0", "4.0"),
            new Place("5.0", "6.0")
        };

        TourConstruction tc = new TourConstruction();
        tc.nearestNeighbor(places);
        int[] tourResults = tc.tourResults;

        assertNotNull(tourResults);
        assertEquals(places.length, tourResults.length);

        boolean[] visited = new boolean[places.length];
        for (int city : tourResults) {
            assertFalse(visited[city]);
            visited[city] = true;
        }

        long totalDistance = 0;
        for (int i = 0; i < tourResults.length; i++) {
            int currCity = tourResults[i];
            int nextCity = tourResults[(i + 1) % tourResults.length];
            totalDistance += tc.distanceMatrix[currCity][nextCity];
        }

        assertEquals(1257L, totalDistance);
    }

    @Test
    @DisplayName ("bachrock: tests initializeRemainingCities with array of one value")
    public void testInitializeRemainingCities() {
        boolean[] remainingCities = new boolean[1];
        initializeRemainingCities(remainingCities);

        assertTrue(remainingCities[0]);
    }

    @Test
    @DisplayName ("bachrock: tests initializeRemainingCities with false array of ten values")
    public void testInitializeRemainingCities() {
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
    public void testInitializeRemainingCities() {
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
    public void testInitializeRemainingCities() {
        boolean[] remainingCities = new boolean[1000000];
        for(boolean remainingCity : remainingCities) {
            assertTrue(initializeRemainingCities(remainingCity));
        }
    }
}
