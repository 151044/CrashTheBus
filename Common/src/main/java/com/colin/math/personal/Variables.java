package com.colin.math.personal;

public class Variables {
    private static VarStore storage = new VarStore();
    public static VarStore getStorage(){
        return storage;
    }
    public static void setStorage(VarStore vars){
        storage = vars;
    }
}
