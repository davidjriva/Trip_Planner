package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
