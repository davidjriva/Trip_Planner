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
        return distList;
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
