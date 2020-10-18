package com.colin.math;

public class Warning extends AbstractResult{
    public Warning(String output, Object result) {
        super("Warning: " + output, result);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }
}
