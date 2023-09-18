package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tco.misc.DistanceCalculator.calculate;
import static java.lang.Math.toRadians;
import static org.junit.jupiter.api.Assertions.*;

public class TestDistanceCalculator {
    static class Geo implements GeographicCoordinate {
        private double degreesLatitude;
        private double degreesLongitude;

        public Geo(Double lat, Double lon){
            this.degreesLatitude = lat;
            this.degreesLongitude = lon;
        }

        public Double latRadians() {
            return toRadians(degreesLatitude);
        }

        public Double lonRadians() {
            return toRadians(degreesLongitude);
        }
    }
    
    // From lecture:
    final Geo origin = new Geo(0.0, 0.0);
    final Geo n180 = new Geo(180.0, 0.0);
    final Geo s180 = new Geo(-180.0, 0.0);
    final Geo e180 = new Geo(0.0, 180.0);
    final Geo w180 = new Geo(0.0, -180.0);

    final static long small = 1L;
    final static long piSmall = Math.round(Math.PI * small);
    final static long piHalfSmall = Math.round(Math.PI / 2.0 * small);

    final static long big = 100000000000000L;
    final static long piBig = Math.round(Math.PI * big);
    final static long piHalfBig = Math.round(Math.PI / 2.0 * big);

    @Test
    @DisplayName("davematt: distance to self should be zero")
    public void testDistanceToSelf() {
        assertEquals(0L, calculate(origin, origin, big));
        assertEquals(0L, calculate(origin, origin, small));
    }

    @Test
    @DisplayName("davematt: distance to same place should be zero (East/West)")
    public void testDistanceToSamePlace() {
        assertEquals(0L, calculate(e180, w180, big));
        //assertEquals(0L, calculate(e180, w180, small));
    }

    @Test
    @DisplayName("driva: distance to same place should be zero (North/South)")
    public void testVerticalDistanceToSamePlace() {
        assertEquals(0L, calculate(n180, s180, big));
        assertEquals(0L, calculate(n180, s180, small));
    }

    final Geo p1 = new Geo(-26.202, 28.028);
    final Geo p2 = new Geo(43.061, 141.350);
    final Geo p3 = new Geo(-34.603, -58.396);
    final Geo p4 = new Geo(51.043, -114.073);
    @Test
    @DisplayName("driva: tests from driva.json")
    public void testDrivaJson() {
        assertEquals(308L, calculate(p1, p2, 142.0));
        assertEquals(403L, calculate(p2, p3, 142.0));
        assertEquals(244L, calculate(p3, p4, 142.0));
        assertEquals(352L, calculate(p4, p1, 142.0));
    }

    final Geo p5 = new Geo(55.7558, 37.6176);
    final Geo p6 = new Geo(49.2827, -123.1207);
    final Geo p7 = new Geo(-33.8752, 151.2135);
    final Geo p8 = new Geo(-22.8274, -43.1498);
    @Test
    @DisplayName("guanhualuo: tests from anjoke.json")
    public void testAnjokeJson() {
        assertEquals(308L, calculate(p5, p6, 1024.0));
        assertEquals(403L, calculate(p6, p7, 1024.0));
        assertEquals(244L, calculate(p7, p8, 1024.0));
        assertEquals(352L, calculate(p8, p5, 1024.0));
    }




}