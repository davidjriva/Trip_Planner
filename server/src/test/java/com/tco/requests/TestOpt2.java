package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

public class TestOpt2 {
    @Test
    @DisplayName("aaouaj: test 1")
    public void testOpt2() {
        Place[] places = {
            new Place("1.0", "2.0"),
            new Place("3.0", "4.0"),
            new Place("5.0", "6.0")
        };
        Opt2 opt2 = new Opt2();
        //opt2.improve();
        //assertNotNull(opt2);
    }

}
