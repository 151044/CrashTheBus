package com.colin.math;

import java.util.function.Function;

public abstract class AbstractResult implements EvalResult{
    private final String out;
    private final Object result;
    public AbstractResult(String output,Object result){
        out = output;
        this.result = result;
    }

    @Override
    public String output() {
        return out;
    }

    @Override
    public <T> T getValue(Function<Object, T> mapper) {
        return mapper.apply(result);
    }
}
