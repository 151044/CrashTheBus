package com.colin.math;

import com.colin.games.bus.Difficulty;

/**
 * Represents a general math expression generator.
 */
public abstract class ExpressionGenerator {
    private final Difficulty diff;
    public ExpressionGenerator(Difficulty diff){
        this.diff = diff;
    }
    public Difficulty getDifficulty(){
        return diff;
    }
    public abstract String getExpression();
}
