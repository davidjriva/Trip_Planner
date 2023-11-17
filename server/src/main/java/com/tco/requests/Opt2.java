package com.tco.requests;

public class Opt2 extends Tour {
    public Opt2() {
        super();
    }

    @Override
    public void improve() {

    }

    public int legDis(int[] route, int i, int j) {
        int distance = 0;
        for (int index = i; index < j && index < route.length - 1; index++) {
            distance += distanceMatrix[route[index]][route[index + 1]];
        }
        return distance;
    }
}