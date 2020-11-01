package com.colin.math.jshell.operands;

public interface BinaryOperand {
    double apply(double first, double second);
    String getFormattedString(double first, double second);
    String getOutputString(double first, double second);
    int points();
}
