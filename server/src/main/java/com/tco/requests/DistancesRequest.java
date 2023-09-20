package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistancesRequest extends Request{
    
    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

    private Places places;
    private Double earthRadius;
    private Distances distances;

    @Override
    public void buildResponse(){
        distances = buildDistanceList();
        log.trace("buildResponse -> {}", this);
    }

    private Distances buildDistanceList(){
        Distances distList = new Distances();
        // Loop through places and add their distances to distList
        for (int i = 0; i < places.size() - 1; i++) {
            for (int j = i + 1; j < places.size(); j++) {
                double distance = computeHaversineDistance(
                    places.get(i).latRadians(), places.get(i).lonRadians(),
                    places.get(j).latRadians(), places.get(j).lonRadians()
                )
                disList.add((long) distance);
            }
        }
        return distList;
    }

    // Build computeHaversineDistance function
    private double computeHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
    double dLat = lat2 - lat1;
    double dLon = lon2 - lon1;

    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
               Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return earthRadius * c;
}

    /* The following methods exist only for testing purposes and are not used
    during normal execution, including the constructor. */

    public DistancesRequest(double earthRadius, Places places){
        super();
        this.requestType = "distances";
        this.earthRadius = earthRadius;
        this.places = places;
    }
    public double earthRadius(){
        return this.earthRadius;
    }
    public Places places(){
        return this.places;
    }
    public Distances distances(){
        return this.distances;
    }
}
