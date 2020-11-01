package com.colin.math;

import com.colin.games.bus.Difficulty;

/**
 * Represents a general math expression generator.
 */
public abstract class ExpressionGenerator {
    private final int points;
    public ExpressionGenerator(int points){
        this.points = points;
    }
    public int getPoints(){
        return points;
    }
    public abstract String getExpression();
}
