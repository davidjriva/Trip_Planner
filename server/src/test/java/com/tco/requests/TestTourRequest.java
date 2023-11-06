package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTourRequest {
    TourRequest request;
    Places places;
    Double earthRadius;
    Double response;

    @BeforeEach
    public void beforeEach() {
        places = new Places();
    }

    @Test
    @DisplayName("driva: test buildResponse() with empty places")
    public void testBuildResponseWithEmptyPlaces() {
        earthRadius = 6371.0;
        response = 15.0;

        places = new Places();

        request = new TourRequest(earthRadius, response, places);
        request.buildResponse();

        places = request.places();
        assertTrue(places.isEmpty());
    }

    @Test
    @DisplayName("driva: test buildResponse with a single place")
    public void testBuildResponseWithSinglePlace() {
        earthRadius = 6371.0;
        response = 15.0;

        places.add(new Place("1.0", "2.0"));

        request = new TourRequest(earthRadius, response, places);
        request.buildResponse();

        places = request.places();
        assertEquals(1, places.size());
    }

    @Test
    @DisplayName("driva: basic tour request")
    public void basicTourRequest() {
        earthRadius = 6371.0;
        response = 15.0;
        
        places.add(new Place("1.0", "2.0"));
        places.add(new Place("3.0", "4.0"));
        places.add(new Place("5.0", "6.0"));

        request = new TourRequest(earthRadius, response, places);
        request.buildResponse();

        places = request.places();
        assertEquals(3, places.size());
    }

    @Test
    @DisplayName("driva: test nearest neighbor with three places")
    public void testNearestNeighborWithThreePlaces() {
        earthRadius = 6371.0;
        response = 15.0;

        places.add(new Place("1.0", "2.0"));
        places.add(new Place("3.0", "4.0"));
        places.add(new Place("5.0", "6.0"));

        request = new TourRequest(earthRadius, response, places);
        request.buildResponse();

        places = request.places();
        assertEquals(3, places.size());

        assertEquals("1.0", places.get(0).get("latitude"));
        assertEquals("2.0", places.get(0).get("longitude"));
        assertEquals("3.0", places.get(1).get("latitude"));
        assertEquals("4.0", places.get(1).get("longitude"));
        assertEquals("5.0", places.get(2).get("latitude"));
        assertEquals("6.0", places.get(2).get("longitude"));
    }

    @Test
    @DisplayName("driva: test nearest neighbor with many places")
    public void testNearestNeighborChangingOrder() {
        earthRadius = 6371.0;
        response = 15.0;

        places.add(new Place("1.0", "2.0"));
        places.add(new Place("3.0", "4.0"));
        places.add(new Place("5.0", "6.0"));
        places.add(new Place("7.0", "8.0"));
        places.add(new Place("9.0", "10.0"));
        places.add(new Place("11.0", "12.0"));

        request = new TourRequest(earthRadius, response, places);
        request.buildResponse();

        places = request.places();
        assertEquals(6, places.size());

        assertEquals("1.0", places.get(0).get("latitude"));
        assertEquals("2.0", places.get(0).get("longitude"));
        assertEquals("3.0", places.get(1).get("latitude"));
        assertEquals("4.0", places.get(1).get("longitude"));
        assertEquals("5.0", places.get(2).get("latitude"));
        assertEquals("6.0", places.get(2).get("longitude"));
        assertEquals("7.0", places.get(3).get("latitude"));
        assertEquals("8.0", places.get(3).get("longitude"));
        assertEquals("9.0", places.get(4).get("latitude"));
        assertEquals("10.0", places.get(4).get("longitude"));
        assertEquals("11.0", places.get(5).get("latitude"));
        assertEquals("12.0", places.get(5).get("longitude"));
    }
}
