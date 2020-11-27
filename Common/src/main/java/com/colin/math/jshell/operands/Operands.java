package com.colin.math.jshell.operands;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Operands {
    private static ThreadLocalRandom tlr = ThreadLocalRandom.current();
    private Operands(){
        throw new AssertionError("No Operands instance allowed.");
    }
    private static List<Class<? extends BinaryOperand>> bOps;
    private static List<Class<? extends UnaryOperand>> uOps;
    static{
        bOps = List.of(Divide.class, Exponent.class, Logarithm.class, Minus.class, Multiply.class, Plus.class);
        uOps = List.of(Cosine.class, Sine.class, Tangent.class);
    }
    public static BinaryOperand getBinaryOp(int available) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return bOps.get(tlr.nextInt(bOps.size())).getConstructor().newInstance();
    }
    public static UnaryOperand getUnaryOp(int available) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return uOps.get(tlr.nextInt(uOps.size())).getConstructor().newInstance();
    }
    public static void addBinaryOps(BinaryOperand... ops){
        bOps.addAll(Stream.of(ops).map(op -> op.getClass()).collect(Collectors.toList()));
    }
    public static void addUnaryOps(UnaryOperand... ops){
        uOps.addAll(Stream.of(ops).map(op -> op.getClass()).collect(Collectors.toList()));
    }
    public static void addBinaryOps(List<BinaryOperand> ops){
        bOps.addAll(ops.stream().map(op -> op.getClass()).collect(Collectors.toList()));
    }
    public static void addUnaryOps(List<UnaryOperand> ops){
        uOps.addAll(ops.stream().map(op -> op.getClass()).collect(Collectors.toList()));
    }
}
