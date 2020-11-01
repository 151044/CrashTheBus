package com.colin.math.jshell.operands;

public interface UnaryOperand {
    double apply(double other);
    String getFormattedString(double other);
    String getOutputString(double other);
    int points();
}
