package com.colin.math.jshell.operands;

import com.colin.math.Round;

public class Cosine implements UnaryOperand{
    @Override
    public double apply(double other) {
        return Math.cos(Round.round(other));
    }

    @Override
    public String getFormattedString(double other) {
        return "Math.cos(" + Round.round(other) + ")";
    }

    @Override
    public String getOutputString(double other) {
        return "cos(" + Round.round(other) + ")";
    }

    @Override
    public int points() {
        return 30;
    }
}
