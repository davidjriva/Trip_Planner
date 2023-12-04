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

    @Test
    @DisplayName("driva: testing response time of zero(no optimizations)")
    public void zeroResponseTourRequest() {
        earthRadius = 6371.0;
        response = 0.0;
        
        Place p1 = new Place("1.0", "2.0");
        Place p2 = new Place("3.0", "4.0");
        Place p3 = new Place("5.0", "6.0");
        Place p4 = new Place("10.0", "50.0");
        Place p5 = new Place("0.0", "0.0");
        
        places.add(p1);
        places.add(p2);
        places.add(p3);
        places.add(p4);
        places.add(p5);

        request = new TourRequest(earthRadius, response, places);
        request.buildResponse();

        places = request.places();
        assertEquals(5, places.size());

        // No optimizations were made:
        assertEquals(places.get(0), p1);
        assertEquals(places.get(1), p2);
        assertEquals(places.get(2), p3);
        assertEquals(places.get(3), p4);
        assertEquals(places.get(4), p5);
    }

    @Test
    @DisplayName("driva: 1Opt is passed since there's not enough time")
    public void Opt1Skipped() {
        earthRadius = 6371.0;
        response = 1.0;

        Places preOptimization = new Places();

        for(int i = 0; i < 1600; i++) {
            String lat = "" + i;
            String lng = "" + i;

            Place tmp = new Place(lat, lng);
            preOptimization.add(tmp);
            places.add(tmp);
        }

        request = new TourRequest(earthRadius, response, places);
        request.buildResponse();
        
        for(int j = 0; j < places.size(); j++){
            assertEquals(preOptimization.get(j), places.get(j)); // ensures no optimizations occured
        }
    }
}
