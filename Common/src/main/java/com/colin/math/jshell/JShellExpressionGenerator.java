package com.colin.math.jshell;

import com.colin.games.bus.Difficulty;
import com.colin.math.ExpressionGenerator;

/**
 * Expression generator for JShell.
 */
public class JShellExpressionGenerator extends ExpressionGenerator {
    public JShellExpressionGenerator(Difficulty diff) {
        super(diff);
    }

    @Override
    public String getExpression() {
        return "";
    }
}
