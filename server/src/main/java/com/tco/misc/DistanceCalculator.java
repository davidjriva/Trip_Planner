package com.tco.misc;

public final class DistanceCalculator {
    //Stop users from instantiating using a default ctor
    private DistanceCalculator(){ }

    // Special Case of the Vincenty formula
    // Credit to: https://en.wikipedia.org/wiki/Great-circle_distance 
    public static Long calculate(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        /*
         *  Input: from Geo object, to Geo object, earthRadius
         *  Output: The distance in earth radius units from your 'from' location to your 'to' location
         */
        Double phi1 = from.latRadians();
        Double phi2 = to.latRadians();
        Double lambda1 = from.lonRadians();
        Double lambda2 = to.lonRadians();
        Double deltaLambda = lambda2 - lambda1;

        Double numerator = Math.pow(Math.cos(phi2)*Math.sin(deltaLambda), 2) + Math.pow(Math.cos(phi1)*Math.sin(phi2) - Math.sin(phi1)*Math.cos(phi2)*Math.cos(deltaLambda), 2);
        numerator = Math.sqrt(numerator);
        
        Double denominator = Math.sin(phi1)*Math.sin(phi2) + Math.cos(phi1)*Math.cos(phi2)*Math.cos(deltaLambda);

        Double deltaSigma = Math.atan2(numerator, denominator);

        Double distance = earthRadius * deltaSigma;
        return Math.round(distance);
    }
}
