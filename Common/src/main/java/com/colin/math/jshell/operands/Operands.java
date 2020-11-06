package com.colin.math.jshell.operands;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Operands {
    private Operands(){
        throw new AssertionError("No Operands instance allowed.");
    }
    private static List<Constructor<? extends BinaryOperand>> bOps = new ArrayList<>();
    private static List<Constructor<? extends UnaryOperand>> uOps = new ArrayList<>();
    static{
        Stream.of(Plus.class, Minus.class, Multiply.class, Divide.class, Logarithm.class, Exponent.class).forEach(c -> {
            try {
                bOps.add(c.getConstructor());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
        Stream.of(Cosine.class, Tangent.class, Sine.class).forEach(c -> {
            try {
                uOps.add(c.getConstructor());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
    public static BinaryOperand getBinaryOp(int available){
        //bOps.stream().map(c -> )
    }
    public static UnaryOperand getUnaryOp(int available){

    }
    public static void addBinaryOps(BinaryOperand... ops){
        Stream.of(ops).forEach(c -> {
            try {
                bOps.add(c.getClass().getConstructor());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
    public static void addUnaryOps(UnaryOperand... ops){
        Stream.of(ops).forEach(c -> {
            try {
                uOps.add(c.getClass().getConstructor());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
    public static void addBinaryOps(List<BinaryOperand> ops){
        ops.stream().forEach(c -> {
            try {
                bOps.add(c.getClass().getConstructor());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
    public static void addUnaryOps(List<UnaryOperand> ops){
        ops.stream().forEach(c -> {
            try {
                uOps.add(c.getClass().getConstructor());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
}
