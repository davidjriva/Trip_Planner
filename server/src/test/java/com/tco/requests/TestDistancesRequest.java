package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDistancesRequest {

    DistancesRequest request;
    Places places;
    Distances distances;

    static final long bigRadius = 1000000000000L;
    static final long bigPi = Math.round(Math.PI * bigRadius);
    static final long bigPiHalf = Math.round(Math.PI / 2.0 * bigRadius);
    

    @BeforeEach
    public void beforeEach() {
        places = new Places();
        request = new DistancesRequest(bigRadius, places);
    }

    @Test
    @DisplayName("davematt: empty places")
    public void testEmptyPlaces() {

        request.buildResponse();

        distances = request.distances();
        assertEquals(0, distances.size());
        assertEquals(0L, distances.total());

        assertEquals(0, request.places().size());
        assertEquals(bigRadius, request.earthRadius());
    }
}