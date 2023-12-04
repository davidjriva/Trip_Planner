package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.Math;

public class TourRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);

    private double earthRadius;
    private double response;
    private Places places = new Places();

    public boolean shouldPerform1Opt(int numPlaces) {
        return response <= ((0.0001 * Math.pow(numPlaces, 2)) + (0.45 * numPlaces) + 100);
    }

    @Override
    public void buildResponse() {
        int numPlaces = places.size();
        if (response != 0.0){
            if(shouldPerform1Opt(numPlaces)){
                Opt1 optimize = new Opt1(places);
                optimize.improve();
                this.places = optimize.places;
            }
        }
        log.trace("buildResponse -> {}", this);
    }

    /* The following methods exist only for testing purposes and are not used
    during normal execution, including the constructor. */

    public TourRequest(Double earthRadius, Double response, Places places){
        super();
        this.requestType = "tour";
        this.earthRadius = earthRadius;
        this.response = response;
        this.places = places;
    }

    public Double earthRadius() {
        return this.earthRadius;
    }

    public Double response() {
        return this.response;
    }

    public Places places() {
        return this.places;
    }
}
