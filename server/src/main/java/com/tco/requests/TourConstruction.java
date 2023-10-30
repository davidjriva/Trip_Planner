package com.tco.requests;

import com.tco.misc.DistanceCalculator;
import com.tco.misc.GeographicCoordinate;

public class TourConstruction {
  int[] tourResults;
  boolean[] unvisitedCities;
  Long[][] distanceMatrix;

  public void createDistanceMatrix(Place[] places) {
        Long[][] matrix = new Long[places.length][places.length];

        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places.length; j++) {
                if (i == j) {
                    matrix[i][j] = 0L; 
                } else {
                    matrix[i][j] = DistanceCalculator.calculate(places[i], places[j], 6371);
                }
            }
        }

        this.distanceMatrix = matrix;
  }

  public void nearestNeighbor(Place[] places) {
    createDistanceMatrix(places);
    int numPlaces = places.length;
    int[] shortestTour = null;
    long shortestDistance = Long.MAX_VALUE;

    for (int startCityIndex = 0; startCityIndex < numPlaces; startCityIndex++) {
      int[] tour = new int[numPlaces];
      int currentCity = startCityIndex;
      boolean[] remainingCities = new boolean[numPlaces];
      int remainingCitiesCount = numPlaces - 1;
      long totalDistance = 0;

      for (int i = 0; i < numPlaces; i++) {
        remainingCities[i] = true;
      }
      remainingCities[currentCity] = false;

      for (int i = 0; i < numPlaces; i++) {
        int minDistance = Integer.MAX_VALUE;
        int nextCity = -1;

        for (int city = 0; city < numPlaces; city++) {
          if (remainingCities[city] && distanceMatrix[currentCity][city] < minDistance) {
            minDistance = distanceMatrix[currentCity][city];
            nextCity = city;
          }
        }

        tour[i] = nextCity;
        totalDistance += minDistance;
        currentCity = nextCity;
        remainingCities[currentCity] = false;
        remainingCitiesCount--;

        if (remainingCitiesCount == 0) {
          break;
        }
      }

      // Return to the starting city
      totalDistance += distanceMatrix[currentCity][startCityIndex];

      if (totalDistance < shortestDistance) {
        shortestTour = tour.clone();
        shortestDistance = totalDistance;
      }
    }

    // Convert the tour to an array of Place objects
    Place[] shortestTourPlaces = new Place[numPlaces];
    for (int i = 0; i < numPlaces; i++) {
      shortestTourPlaces[i] = places[shortestTour[i]];
    }

    // Assign the result to tourResults and potentially store other information
    tourResults = shortestTour;
  }
}
