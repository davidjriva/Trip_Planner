package com.tco.requests;

import static java.lang.Double.parseDouble;
import static java.lang.Math.toRadians;

class Place extends HashMap<String, String> implements GeographicCoordinate{
    //For testing purposes
    Place (String lat, String lon){
        this.put("latitude", lat);
        this.put("longitude", lon)
    }

    //Required for GSON
    Place() {}

    //Implements the GeographicCoordinate Interface
    public Double latRadians(){
        return toRadians(parseDouble(this.get("latitude")));
    }

    public Double lonRadians(){
        return toRadians(parseDouble(this.get("longitude")));
    }

    public String id(){
        return this.get("id");
    }
}
