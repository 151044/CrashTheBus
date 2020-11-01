package com.colin.math.personal;

public class Warning extends AbstractResult{
    public Warning(String output, Object result) {
        super("Warning: " + output, result);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }
}
