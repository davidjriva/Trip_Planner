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
        assertEquals(found, 16);

        assertEquals(places.get(0).get("continent"), "NA");
        assertEquals(places.get(0).get("altitude"), "5170");
        assertEquals(places.get(0).get("iso_country"), "US");
        assertEquals(places.get(0).get("latitude"), "40.0332984924");
        assertEquals(places.get(0).get("name"), "Dave's Airport");
        assertEquals(places.get(0).get("municipality"), "Louisville");
        assertEquals(places.get(0).get("iso_region"), "US-CO");
        assertEquals(places.get(0).get("index"), "1");
        assertEquals(places.get(0).get("id"), "0CO1");
        assertEquals(places.get(0).get("longitude"), "-105.124000549");
    }
}
