package com.tco.requests;

import java.util.ArrayList;

public class Distances extends ArrayList<Long> {
    public long total(){
        long sum = 0;
        for (long leg : this){
            sum += leg;
        }
        return sum;
    }
}
