package com.tco.requests;

public class Opt2 extends Tour {
    public Opt2() {
        super();
    }

    @Override
    public void improve() {
        int[] route = tourResults;
        boolean improvement = true;
        int n = route.length;

        while (improvement) {
            improvement = false;
            for (int i = 0; i <= n - 3; i++) {
                for (int k = i + 2; k <= n - 1; k++) {
                    if (twoOptImproves(route, i, k)) {
                        twoOptReverse(route, i + 1, k);
                        improvement = true;
                    }
                }
            }
        }
    }

    public boolean twoOptImproves(int[] route, int i, int k) {
        int n = route.length;
        return (legDis(route, i, k) + legDis(route, i + 1, k + 1) < legDis(route, i, i + 1) + legDis(route, k, k + 1));
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