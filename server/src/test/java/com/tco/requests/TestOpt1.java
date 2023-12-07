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
        Long[][] distanceMatrix = op1.getDistanceMatrix();

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
        Long[][] distanceMatrix = op1.getDistanceMatrix();

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
        op1.shorter(places);
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
        op1.shorter(places);
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
            Long[][] dMat = op1.getDistanceMatrix();
            totalDistance += dMat[currCity][nextCity];
        }

        assertEquals(1257L, totalDistance);
    }

    @Test
    @DisplayName("alexr11: test places sort")
    public void testopt1sort() {
        Places places = new Places();
        places.add(new Place("50.0", "50.0"));
        places.add(new Place("25.0", "90.0"));
        places.add(new Place("0.0", "0.0"));
        places.add(new Place("-90.0", "-180.0"));
        places.add(new Place("90.0", "180.0"));
        places.add(new Place("-90.0", "180.0"));
        places.add(new Place("90.0", "-180.0"));

        Places startPlaces = new Places();

        for (int i = 0; i < places.size(); i++)
        {
            startPlaces.add(places.get(i));
        }

        Opt1 op1 = new Opt1(places);
        op1.improve();
        int[] tourResults = op1.tourResults;
        places = op1.places;

        assertNotNull(tourResults);
        assertEquals(places.size(), tourResults.length);
        assertEquals(places.size(), startPlaces.size());

        assertEquals(places.get(0), startPlaces.get(0));
        assertEquals(places.get(1), startPlaces.get(1));
        assertEquals(places.get(2), startPlaces.get(4));
        assertEquals(places.get(3), startPlaces.get(6));
        assertEquals(places.get(4), startPlaces.get(2));
        assertEquals(places.get(5), startPlaces.get(3));
        assertEquals(places.get(6), startPlaces.get(5));
    }
}
