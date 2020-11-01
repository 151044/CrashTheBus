package com.colin.math.personal;

import java.util.Random;

public class RandomUtils {
    private static Random rand = new Random();
    private RandomUtils(){
        throw new AssertionError("RandomUtils cannot be initialized.");
    }
    public static double getNormal(double mean, double deviate){
        return rand.nextGaussian() * deviate + mean;
    }
}
