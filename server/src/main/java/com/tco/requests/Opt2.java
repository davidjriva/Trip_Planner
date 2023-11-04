package com.tco.requests;

class Opt2 extends Tour {
    @Override
    void improve() {
        boolean improvement = true;
        int n = tourResults.length; 
        long initialDistance = distance;  
        while(improvement) {
            improvement = false;
            for(int i = 0; i <= n-3; i++) {
                for(int k = i + 2; k <= n-1; k++) {
                    if(twoOptImproves(tourResults, i, k)) {
                        twoOptReverse(tourResults, i+1, k);
                        improvement = true;
                    }
                }
            }
        }
    }

    public long calcDist(int[] tour) {
        long newDistance = 0;
        return -1;
    }

    public boolean twoOptImproves(int[] route, int i, int k) {
        return false;
    }

    public void twoOptReverse(int[] route, int i1, int k) {
        
    }
}