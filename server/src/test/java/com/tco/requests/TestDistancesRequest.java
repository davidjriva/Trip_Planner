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
        
    }

    @Test
    @DisplayName("davematt: empty places")
    public void testEmptyPlaces() {
        long radius = 1000000000000L;
        request = new DistancesRequest(radius, places);

        request.buildResponse();

        distances = request.distances();
        assertEquals(0, distances.size());
        assertEquals(0L, distances.total());

        assertEquals(0, request.places().size());
        assertEquals(bigRadius, request.earthRadius());
    }

    @Test
    @DisplayName("alexr11: test 1 place")
    public void testOnePlaces() {
        long radius = 500L;
        request = new DistancesRequest(radius, places);
        places.add(new Place("650","540"));
        request.buildResponse();

        distances = request.distances();
        assertEquals(1, distances.size());
        assertEquals(0L, distances.total());
        assertEquals(0L, distances.get(0));

        assertEquals(1, request.places().size());
        assertEquals(radius, request.earthRadius());
    }

    @Test
    @DisplayName("alexr11: test 2 places")
    public void testTwoPlaces() {
        long radius = 1000L;
        request = new DistancesRequest(radius, places);

        places.add(new Place("66.0","-150.0"));
        places.add(new Place("-30.0","-140.0"));

        request.buildResponse();

        distances = request.distances();
        assertEquals(2, distances.size());
        assertEquals(3362L, distances.total());
        assertEquals(1681L, distances.get(0));
        assertEquals(1681L, distances.get(1));

        assertEquals(2, request.places().size());
        assertEquals(radius, request.earthRadius());
    }

    @Test
    @DisplayName("alexr11: test 3 places")
    public void testThreePlaces() {
        long radius = 20000000L;
        request = new DistancesRequest(radius, places);

        places.add(new Place("105.55","50.5"));
        places.add(new Place("-90.808","-430.20"));
        places.add(new Place("75.404","-103.1"));

        request.buildResponse();

        distances = request.distances();
        assertEquals(3, distances.size());
        assertEquals(117912548L, distances.total());
        assertEquals(57542450L, distances.get(0));
        assertEquals(57971336L, distances.get(1));
        assertEquals(2398762L, distances.get(2));

        assertEquals(3, request.places().size());
        assertEquals(radius, request.earthRadius());
    }

    @Test
    @DisplayName("alexr11: test 4 places")
    public void testFourPlaces() {
        long radius = 142L;
        request = new DistancesRequest(radius, places);

        places.add(new Place("-26.202","28.028"));
        places.add(new Place("43.061","141.350"));
        places.add(new Place("-34.603","-58.396"));
        places.add(new Place("51.043","-114.073"));

        request.buildResponse();

        distances = request.distances();
        assertEquals(4, distances.size());
        assertEquals(1307L, distances.total());
        assertEquals(308L, distances.get(0));
        assertEquals(403L, distances.get(1));
        assertEquals(244L, distances.get(2));
        assertEquals(352L, distances.get(3));

        assertEquals(4, request.places().size());
        assertEquals(radius, request.earthRadius());
    }

    @Test
    @DisplayName("driva: test 4 places large")
    public void testFourPlacesLarge() {
        long radius = 3483705L;
        request = new DistancesRequest(radius, places);

        places.add(new Place("64.074", "-141.938"));
        places.add(new Place("69.165", "172.265"));
        places.add(new Place("-28.603", "-65.108"));
        places.add(new Place("-30.511", "145.125"));
        
        request.buildResponse();

        distances = request.distances();
        assertEquals(4, distances.size());
        assertEquals(22549952L, distances.total());
        assertEquals(1117741L, distances.get(0));
        assertEquals(7783278L, distances.get(1));
        assertEquals(6945620L, distances.get(2));
        assertEquals(6703313L, distances.get(3));

        assertEquals(4, request.places().size());
        assertEquals(radius, request.earthRadius());
    }
}