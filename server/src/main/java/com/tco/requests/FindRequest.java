package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.database.sqlGuide.Database;
import com.tco.database.sqlGuide.Place;
import com.tco.database.sqlGuide.Places;

public class FindRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(FindRequest.class);

    private String match;
    private int limit;
    private int found;
    private Places places;

    @Override
    public void buildResponse() {
        try{
            found = Database.found(match);
            places = Database.places(match, limit);
        } catch(Exception e){
            log.error("Error with: " + e.getMessage());
        }
        log.trace("buildResponse -> {}", this);
    }


    /* The following methods exist only for testing purposes and are not used
    during normal execution, including the constructor. */


    public FindRequest(String match, int limit){
        super();
        this.requestType = "find";
        this.match = match;
        this.limit = limit;
    }

    public String match(){
        return this.match;
    }

    public int found(){
        return this.found;
    }

    public Places places(){
        return this.places;
    }

    public int limit(){
        return this.limit;
    }
}
