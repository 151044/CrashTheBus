package com.colin.math.jshell.operands;

import com.colin.math.Round;

public class Multiply implements BinaryOperand{
    @Override
    public double apply(double first, double second) {
        return Round.round(first) * Round.round(second);
    }

    @Override
    public String getFormattedString(double first, double second) {
        return Round.round(first) + " * " + Round.round(second);
    }

    @Override
    public String getOutputString(double first, double second) {
        return getFormattedString(first,second);
    }

    @Override
    public int points() {
        return 20;
    }
}
