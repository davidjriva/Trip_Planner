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
}
