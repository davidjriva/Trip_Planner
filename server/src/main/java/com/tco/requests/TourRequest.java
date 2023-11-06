package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TourRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(FindRequest.class);

    private Double earthRadius;
    private Double response;
    private Places places;

    @Override
    public void buildResponse() {
        Opt1 optimize = new Opt1(places);
        optimize.improve();
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
