package com.colin.math;

public class Success extends AbstractResult{
    public Success(String output,Object result) {
        super(output,result);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

}
