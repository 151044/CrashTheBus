package com.colin.math;

import java.util.HashMap;
import java.util.Map;

public class VarStore {
    private Map<String,Double> vars = new HashMap<>();
    public void addVariable(String name, double toAdd){
        vars.put(name,toAdd);
    }
    public double getVariable(String varName){
        return vars.get(varName);
    }
    public boolean hasVariable(String varName){
        return vars.containsKey(varName);
    }
    public String dump(){
        return vars.toString();
    }
}

