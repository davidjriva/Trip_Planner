package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOpt1 {
    @Test
    @DisplayName("driva: test single place distrance matrix")
    public void testCreateDistanceMatrixSinglePlace() {
        Places places = new Places();
        places.add(new Place("1.0", "2.0"));

        Opt1 op1 = new Opt1(places);
        op1.createDistanceMatrix(places);
        Long[][] distanceMatrix = op1.distanceMatrix;

        assertNotNull(distanceMatrix);
        assertEquals(places.size(), distanceMatrix.length);
        assertEquals(0L, distanceMatrix[0][0].longValue());
    }

    @Test
    @DisplayName("driva: test multiple places distance matrix")
    public void testCreateDistanceMatrixMultiplePlaces() {
        Places places = new Places();
        places.add(new Place("1.0", "2.0"));
        places.add(new Place("3.0", "4.0"));
        places.add(new Place("5.0", "6.0"));

        Opt1 op1 = new Opt1(places);

        op1.createDistanceMatrix(places);
        Long[][] distanceMatrix = op1.distanceMatrix;

        assertNotNull(distanceMatrix);
        assertEquals(places.size(), distanceMatrix.length);
        assertEquals(0L, distanceMatrix[0][0].longValue());
        assertEquals(314L, distanceMatrix[0][1].longValue());
        assertEquals(629L, distanceMatrix[0][2].longValue());
    }


    @Test
    @DisplayName("driva: test nn single place")
    public void testNearestNeighborSinglePlace() {
        Places places = new Places();
        places.add(new Place("1.0", "2.0"));

        Opt1 op1 = new Opt1(places);
        op1.nearestNeighbor(places);
        int[] tourResults = op1.tourResults;

        assertNotNull(tourResults);
        assertEquals(places.size(), tourResults.length);
        assertEquals(0, tourResults[0]);
    }

    @Test
    @DisplayName("driva: test nn multiple places")
    public void testNearestNeighborMultiplePlaces() {
        Places places = new Places();
        places.add(new Place("1.0", "2.0"));
        places.add(new Place("3.0", "4.0"));
        places.add(new Place("5.0", "6.0"));

        Opt1 op1 = new Opt1(places);
        op1.nearestNeighbor(places);
        int[] tourResults = op1.tourResults;

        assertNotNull(tourResults);
        assertEquals(places.size(), tourResults.length);

        boolean[] visited = new boolean[places.size()];
        for (int city : tourResults) {
            assertFalse(visited[city]);
            visited[city] = true;
        }

        long totalDistance = 0;
        for (int i = 0; i < tourResults.length; i++) {
            int currCity = tourResults[i];
            int nextCity = tourResults[(i + 1) % tourResults.length];
            totalDistance += op1.distanceMatrix[currCity][nextCity];
        }

        assertEquals(1257L, totalDistance);
    }
}
