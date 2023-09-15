package com.tco.misc;

import static java.lang.Double.parseDouble;
import static java.lang.Math.toRadians;

// A data transfer interface for parameter passing
public interface GeographicCoordinate {
    //radian values
    public Double latRadians();
    public Double lonRadians();
}
