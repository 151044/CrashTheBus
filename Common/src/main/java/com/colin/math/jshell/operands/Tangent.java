package com.colin.math.jshell.operands;

import com.colin.math.Round;

public class Tangent implements UnaryOperand{
    @Override
    public double apply(double other) {
        return Math.tan(Round.round(other));
    }

    @Override
    public String getFormattedString(double other) {
        return "Math.tan(" + Round.round(other) + ")";
    }

    @Override
    public String getOutputString(double other) {
        return "tan(" + Round.round(other) + ")";
    }

    @Override
    public int points() {
        return 30;
    }
}
