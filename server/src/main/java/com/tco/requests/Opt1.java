package com.tco.requests;

class Opt1 extends Tour {
    public Places places;

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
                Place cityPlace = places.get(city);
                if (cityPlace != null) {
                    String cityName = cityPlace.get("name");

                    String defaultDisplayName = cityPlace.get("defaultDisplayName");
                    if (defaultDisplayName != null && !defaultDisplayName.isEmpty()) {
                        cityPlace.put("name", defaultDisplayName);
                    } else {
                        cityPlace.put("name", null);
                    }
                    
                    reorderedPlaces.add(cityPlace);
                }
            }
            this.places = reorderedPlaces;
        }
    }
}
