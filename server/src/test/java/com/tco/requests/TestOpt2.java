package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

public class TestOpt2 {

    @Test
    @DisplayName("driva: test two opt improves")
    void testTwoOptImproves() {
        Opt2 optimizer = new Opt2();
        int[] route = {1, 2, 3, 4, 5};
        boolean improves = optimizer.twoOptImproves(route, 0, 3);
        assertFalse(improves);
    }

    @Test
    @DisplayName("driva: test two opt reverse")
    void testTwoOptReverse() {
        Opt2 optimizer = new Opt2();
        int[] route = {1, 2, 3, 4, 5};
        optimizer.twoOptReverse(route, 1, 3);
        int[] expected = {1, 4, 3, 2, 5};
        assertArrayEquals(expected, route);
    }

    @Test
    @DisplayName("driva: test leg dist")
    void testLegDis() {
        Opt2 optimizer = new Opt2();
        int[] route = {1, 2, 3, 4, 5};
        int distance = optimizer.legDis(route, 1, 3);
        assertEquals(0, distance);
    }
}
