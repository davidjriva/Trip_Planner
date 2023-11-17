package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

public class TestOpt2 {
    @Test
    @DisplayName("driva: test 2opt leg dist")
    public void testLegDis() {
        Opt2 optimizer = new Opt2();
        optimizer.distanceMatrix = new Long[][]{{0L, 5L, 9L}, {5L, 0L, 10L}, {9L, 10L, 0L}};
        int[] route = {0, 1, 2};

        assertEquals(15, optimizer.legDis(route, 0, 3));
        assertEquals(15, optimizer.legDis(route, 0, 2));
    }
}
