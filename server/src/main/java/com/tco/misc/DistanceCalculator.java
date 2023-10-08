package com.tco.misc;

public final class DistanceCalculator {
    //Stop users from instantiating using a default ctor
    private DistanceCalculator(){ }

    // Special Case of the Vincenty formula
    // Credit to: https://en.wikipedia.org/wiki/Great-circle_distance 
    public static Long calculate(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        /*
         *  Input -> from: a GeographicCoordinate object representing the start coordinate
                     to: a GeographicCoordinate object representing the end coordinate
                     earthRadius: a Double representing the earth radius in whichever unit is being used.
         *  Output: A Long value representing the distance between your 'from' and 'to' locations in the earthRadius used.
         */
        Double phi1 = from.latRadians();
        Double phi2 = to.latRadians();
        Double lambda1 = from.lonRadians();
        Double lambda2 = to.lonRadians();
        Double deltaLambda = lambda2 - lambda1;

        Double numerator = calculateNumerator(phi1, phi2, deltaLambda);
        
        Double denominator = Math.sin(phi1)*Math.sin(phi2) + Math.cos(phi1)*Math.cos(phi2)*Math.cos(deltaLambda);

        Double deltaSigma = Math.atan2(numerator, denominator);

        Double distance = earthRadius * deltaSigma;
        Long roundedDistance = Math.round(distance);

        return roundedDistance;
    }

    public static Double calculateNumerator(Double phi1, Double phi2, Double deltaLambda){
        Double numerator = Math.pow(Math.cos(phi2)*Math.sin(deltaLambda), 2) + Math.pow(Math.cos(phi1)*Math.sin(phi2) - Math.sin(phi1)*Math.cos(phi2)*Math.cos(deltaLambda), 2);
        Double sqrtNumerator = Math.sqrt(numerator);

        return sqrtNumerator;
    }
}
