package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.tco.database.sqlGuide.Places;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFindRequest {
    FindRequest request;
    Places places;
    int found;

    @BeforeEach
    public void beforeEach() {
    places = new Places();
    }

    @Test
    @DisplayName("driva: request with match='dave' limit=3")
    public void testEmptyPlaces() {
        String match = "dave";
        int limit = 3;

        request = new FindRequest(match, limit);
        request.buildResponse();

        places = request.places();
        assertEquals(3, places.size());

        found = request.found();
        assertEquals(found, 28);
        assertEquals(places.get(0).get("continent"), "North America");
        assertEquals(places.get(0).get("altitude"), "5170");
        assertEquals(places.get(0).get("country"), "United States");
        assertEquals(places.get(0).get("latitude"), "40.0332984924");
        assertEquals(places.get(0).get("name"), "Dave's Airport");
        assertEquals(places.get(0).get("municipality"), "Louisville");
        assertEquals(places.get(0).get("region"), "Colorado");
        assertEquals(places.get(0).get("index"), "1");
        assertEquals(places.get(0).get("id"), "0CO1");
        assertEquals(places.get(0).get("longitude"), "-105.124000549");
        
    }

    @Test
    @DisplayName("alexr11: test table joins")
    public void testjoins() {
        String match = "husky";
        int limit = 2;

        request = new FindRequest(match, limit);
        request.buildResponse();

        places = request.places();
        assertEquals(2, places.size());

        found = request.found();
        assertEquals(found, 2);
        assertEquals(places.get(0).get("name"), "Husky Airport");
        assertEquals(places.get(0).get("id"), "MX-0310");
        assertEquals(places.get(0).get("continent"), "North America");
        assertEquals(places.get(0).get("country"), "Mexico");
        assertEquals(places.get(0).get("region"), "Tamaulipas");
        assertEquals(places.get(0).get("municipality"), "Gonzalez");
        assertEquals(places.get(0).get("latitude"), "22.7891");
        assertEquals(places.get(0).get("longitude"), "-98.49");

        assertEquals(places.get(1).get("name"), "Husky Haven Airport");
        assertEquals(places.get(1).get("id"), "P32");
        assertEquals(places.get(1).get("continent"), "North America");
        assertEquals(places.get(1).get("country"), "United States");
        assertEquals(places.get(1).get("region"), "Pennsylvania");
        assertEquals(places.get(1).get("municipality"), "Montrose");
        assertEquals(places.get(1).get("latitude"), "41.78900146484375");
        assertEquals(places.get(1).get("longitude"), "-75.88990020751953");
    }

    @Test
    @DisplayName("alexr11: test 0 limit")
    public void testZeroLimit() {
        String match = "ryp";
        int limit = 0;

        request = new FindRequest(match, limit);
        request.buildResponse();

        places = request.places();
        assertEquals(4, places.size());

        found = request.found();
        assertEquals(found, 4);
        assertEquals(places.get(0).get("name"), "Plum Island Airport");
        assertEquals(places.get(0).get("id"), "2B2");
        assertEquals(places.get(0).get("continent"), "North America");
        assertEquals(places.get(0).get("country"), "United States");
        assertEquals(places.get(0).get("region"), "Massachusetts");
        assertEquals(places.get(0).get("municipality"), "Newburyport");
        assertEquals(places.get(0).get("latitude"), "42.7958984375");
        assertEquals(places.get(0).get("longitude"), "-70.84120178222656");
    }
}
