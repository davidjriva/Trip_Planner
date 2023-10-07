package com.tco.database;

import com.tco.requests.Places;
import com.tco.requests.Place;

public class Report {

    public static void printPlaces(Places places) {
        for (Place place : places) {
            System.out.println(place);
        }
    }
}