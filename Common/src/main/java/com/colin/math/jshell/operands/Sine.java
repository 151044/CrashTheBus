package com.colin.math.jshell.operands;

import com.colin.math.Round;

public class Sine implements UnaryOperand{
    @Override
    public double apply(double other) {
        return Math.sin(Round.round(other));
    }

    @Override
    public String getFormattedString(double other) {
        return "Math.sin(" + Round.round(other) + ")";
    }

    @Override
    public String getOutputString(double other) {
        return "sin(" + Round.round(other) + ")";
    }

    @Override
    public int points() {
        return 0;
    }
}
