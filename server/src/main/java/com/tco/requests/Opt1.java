package com.tco.requests;

class Opt1 extends Tour {
    private Places places;

    public Opt1(Places places) {
        this.places = places;
    }

    @Override
    void improve() {
        if (places != null && !places.isEmpty()) {
            shorter(places);
            int[] tourResults = getTourResults();

            Places reorderedPlaces = new Places();
            for (int city : tourResults) {
                reorderedPlaces.add(places.get(city));
            }
            this.places = reorderedPlaces;
        }
    }
}