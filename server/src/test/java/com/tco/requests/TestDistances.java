package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDistances {
    @Test
    @DisplayName("davematt:")
    public void testNoDistances(){
        Distances distances = new Distances();
        assertEquals(0, distances.size());
        assertEquals(0L, distances.total());
    }

    @Test
    @DisplayName("davematt:")
    public void testOneDistances() {
        Distances distances = new Distances();
        distances.add(12345L);
        assertEquals(1, distances.size());
        assertEquals(12345L, distances.total());
    }

    @Test
    @DisplayName("davematt:")
    public void testDistances() {
        Distances distances = new Distances();
        distances.add(5L);
        distances.add(40L);
        distances.add(300L);
        distances.add(2000L);
        distances.add(10000L);
        assertEquals(5, distances.size());
        assertEquals(12345L, distances.total());
    }

    @Test
    @DisplayName("driva: Testing negative distances")
    public void testNegativeDistances() {
        Distances distances = new Distances();
        distances.add(-5L);
        distances.add(5L);
        distances.add(-3L);
        distances.add(2L);
        assertEquals(4, distances.size());
        assertEquals(-1L, distances.total());
    }

    @Test
    @DisplayName("driva: Testing same distances")
    public void sameDistances() {
        Distances distances = new Distances();
        distances.add(1L);
        distances.add(1L);
        distances.add(1L);
        distances.add(1L);
        distances.add(1L);
        distances.add(1L);
        distances.add(1L);
        distances.add(1L);
        assertEquals(8, distances.size());
        assertEquals(8L, distances.total());
    }
} 
