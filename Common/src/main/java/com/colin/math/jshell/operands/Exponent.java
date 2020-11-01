package com.colin.math.jshell.operands;

import com.colin.math.Round;

public class Exponent implements BinaryOperand{
    @Override
    public double apply(double first, double second) {
        return Math.pow(Round.round(first),Round.round(second));
    }

    @Override
    public String getFormattedString(double first, double second) {
        return "Math.pow(" + first + "," + second + ")";
    }

    @Override
    public String getOutputString(double first, double second) {
        return first + " ^ " + second;
    }

    @Override
    public int points() {
        return 0;
    }
}
