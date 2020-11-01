package com.colin.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Round {
    public static final int DEFAULT_PLACES = 5;
    private Round(){
        throw new AssertionError("Round instance created.");
    }
    public static double round(double toRound, int places){
        if(places < 0){
            throw new IllegalArgumentException("Round.round(double,int) called with negative places argument.");
        }
        return new BigDecimal(Double.toString(toRound)).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

    public static double round(double toRound) {
        return round(toRound,DEFAULT_PLACES);
    }
}
