package com.tco.requests;

import java.util.ArrayList;

class Opt1 extends Tour {
    public Places places;

    public Opt1(Places places) {
        this.places = places;
        this.numPlaces = places.size();
    }

    @Override
    void improve() {
        if (places != null && !places.isEmpty()) {
            shorter(places);
            int[] tourResults = getTourResults();

            sortByIndices(places, tourResults);
        }
    }
}
