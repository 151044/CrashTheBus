package com.colin.math.personal;

public class Failure extends AbstractResult{
    public Failure(String output) {
        super("Failed to parse statement: " + output,null);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }
}
