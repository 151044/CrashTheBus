package com.colin.math.jshell.operands;

import com.colin.math.Round;

public class Logarithm implements BinaryOperand{
    @Override
    public double apply(double first, double second) {
        return Math.log(Round.round(first)) / Math.log(Round.round(second));
    }

    @Override
    public String getFormattedString(double first, double second) {
        return "Math.log(" + Round.round(first) + ") / Math.log(" + Round.round(second) + ")";
    }

    @Override
    public String getOutputString(double first, double second) {
        return "log(" + Round.round(first)+ ", " + Round.round(second) + ")";
    }

    @Override
    public int points() {
        return 50;
    }
}
