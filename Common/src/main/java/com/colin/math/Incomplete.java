package com.colin.math;

public class Incomplete extends AbstractResult{
    public Incomplete(String output) {
        super("Input statement is incomplete: " + output,null);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }
}
