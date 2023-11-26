package com.tco.requests;

public class Opt2 extends Tour {
    public Opt2() {
        super();
    }

    @Override
    public void improve() {

    }

    public void twoOptReverse(int[] route, int i1, int k) {
        while (i1 < k) {
            int temp = route[i1];
            route[i1] = route[k];
            route[k] = temp;
            i1++;
            k--;
        }
    }

    public int legDis(int[] route, int i, int j) {
        int distance = 0;
        for (int index = i; index < j && index < route.length - 1; index++) {
            distance += distanceMatrix[route[index]][route[index + 1]];
        }
        return distance;
    }
}