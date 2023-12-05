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

    private static void sortByIndices(Places places, int[] indices) {
        for (int i = 0; i < indices.length; i++) {
            while (i != indices[i]) {
                int index = indices[i];
                swap(places, i, index);
                swap(indices, i, index);
            }
        }
    }

    private static void swap(Places places, int i, int j) {
        Place temp = places.get(i);
        places.set(i, places.get(j));
        places.set(j, temp);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
