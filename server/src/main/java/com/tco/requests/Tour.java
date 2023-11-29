package com.tco.requests;

import com.tco.misc.DistanceCalculator;
import com.tco.misc.GeographicCoordinate;

abstract class Tour {
    Long[][] distanceMatrix;
    int[] tourResults;

    public void shorter(Places places) {
        nearestNeighbor(places);
    }

    public void createDistanceMatrix(Places places) {
        Long[][] matrix = new Long[places.size()][places.size()];
        int numPlaces = places.size();

        for (int i = 0; i < numPlaces; i++) {
            for (int j = 0; j < numPlaces; j++) {
                if (i == j) {
                    matrix[i][j] = 0L;
                } else {
                    matrix[i][j] = DistanceCalculator.calculate(places.get(i), places.get(j), 6371);
                }
            }
        }

        this.distanceMatrix = matrix;
    }

    public void nearestNeighbor(Places places) {
        createDistanceMatrix(places);
        int numPlaces = places.size();
        int[] shortestTour = null;
        long shortestDistance = Long.MAX_VALUE;

        int startCityIndex = 0;

        int[] tour = new int[numPlaces];
        int currentCity = startCityIndex;
        boolean[] remainingCities = new boolean[numPlaces];
        int remainingCitiesCount = numPlaces;
        long totalDistance = 0;

        initializeRemainingCities(remainingCities);

        for (int i = 0; i < numPlaces; i++) {
            tour[i] = currentCity;
            remainingCities[currentCity] = false;
            remainingCitiesCount--;

            if (remainingCitiesCount == 0) {
                break;
            }

            long minDistance = Long.MAX_VALUE;
            int nextCity = -1;

            for (int city = 0; city < numPlaces; city++) {
                if (remainingCities[city] && distanceMatrix[currentCity][city] < minDistance) {
                    minDistance = distanceMatrix[currentCity][city];
                    nextCity = city;
                }
            }

            totalDistance += minDistance;
            currentCity = nextCity;
        }

        totalDistance += distanceMatrix[currentCity][startCityIndex];

        if (totalDistance < shortestDistance) {
            shortestTour = tour.clone();
            shortestDistance = totalDistance;
        }

        tourResults = shortestTour;
    }


    public static void initializeRemainingCities(boolean[] remainingCities) {
        for (int i = 0; i < remainingCities.length; i++) {
            remainingCities[i] = true;
        }
    }

    public int[] getTourResults() {
        return tourResults;
    }

    public Long[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    abstract void improve();
}
