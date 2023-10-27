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
}
