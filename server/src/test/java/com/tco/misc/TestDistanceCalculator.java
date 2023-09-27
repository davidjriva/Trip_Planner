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
        assertEquals(1318L, calculate(p5, p6, 1024.0));
        assertEquals(2009L, calculate(p6, p7, 1024.0));
        assertEquals(2175L, calculate(p7, p8, 1024.0));
        assertEquals(1854L, calculate(p8, p5, 1024.0));
    }

    final Geo p9 = new Geo(-33.9, -151.2);
    final Geo p10 = new Geo(21.3, 157.9);
    final Geo p11 = new Geo(25.0, 121.6);
    final Geo p12 = new Geo(-12.0, 77.0);
    @DisplayName("bachrock: tests from bachrock.json")
    public void testBachrockJson() {
        assertEquals(998L, calculate(p9, p10, 1029));
        assertEquals(2109L, calculate(p10, p11, 1029));
        assertEquals(1650L, calculate(p11, p12, 1029));
        assertEquals(3328L, calculate(p12, p9, 1029));
    }

    final Geo pt1 = new Geo(64.074, -141.938);
    final Geo pt2 = new Geo(69.165, 172.265);
    final Geo pt3 = new Geo(-28.603, -65.108);
    final Geo pt4 = new Geo(-30.511, 145.125);
    
    @Test
    @DisplayName("alexr11: tests from alexr11.json and small distance tests")
    public void testAlexRubsamJson() {
        assertEquals(1117741L, calculate(pt1, pt2, 3483705.0));
        assertEquals(7783278L, calculate(pt2, pt3, 3483705.0));
        assertEquals(6945620L, calculate(pt3, pt4, 3483705.0));
        assertEquals(6703313L, calculate(pt4, pt1, 3483705.0));
    }
    
    final Geo pl1 = new Geo(40.70986392889512, -73.99067902670826);
    final Geo pl2 = new Geo(-22.827408929741306, -43.14976462493434);
    final Geo pl3 = new Geo(48.85724432669951, 2.35080897215182);
    final Geo pl4 = new Geo(-33.87517392788854, 151.2134527627967);
    
    @Test
    @DisplayName("aaouaj: tests from aaouaj.json")
    public void testAaouajJson() {
        assertEquals(3042L, calculate(pl1, pl2, 2500));
        assertEquals(3594L, calculate(pl2, pl3, 2500));
        assertEquals(6656L, calculate(pl3, pl4, 2500));
        assertEquals(6275L, calculate(pl4, pl1, 2500));
    }


}
