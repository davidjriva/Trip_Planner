package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(FindRequest.class);

    @Override
    public void buildResponse() {
        // Add logic to build a Find Request based on selected parameters
        log.trace("buildResponse -> {}", this);
    }
}