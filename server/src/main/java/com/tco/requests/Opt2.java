package com.tco.requests;

class Opt2 extends Tour {
    @Override
    void improve() {
        twoOpt(tourResults);
    }

    public long calcDist(int[] tour) {
        long newDistance = 0;
        return -1;
    }

    public void twoOpt(int[] route) {
        boolean improvement = true;
        while(improvement) {
            int n = route.length;
            for(int i = 0; i <= n - 3; i++) {
                for(int k = i + 2; k <= n - 1; k++) {
                    if(twoOptImproves(route, i, k)) {
                        twoOptReverse(route, i + 1, k);
                        improvement = true;
                    }
                }
            }
        }
    }

    public boolean twoOptImproves(int[] route, int i, int k) {
        return (legDis(route, i, k) + legDis(route, i + 1, k + 1) < legDis(route, i, i + 1) + legDis(route, k, k + 1));
    }

    public void twoOptReverse(int[] route, int i1, int k) {
        while(i1 < k) {
            int temp = route[i1];
            route[i1] = route[k];
            route[k] = temp;
            i1++;
            k--;
        }
    }

    public int legDis(int[] route, int i, int j) {
        return 0;
    }
}