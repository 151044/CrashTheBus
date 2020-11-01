package com.colin.math.personal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EvalSnippet {
    private static final Pattern SYM = Pattern.compile("[+\\-*/^()]");
    private final String toEval;

    public EvalSnippet(String toEval) {
        this.toEval = toEval;
    }



    private boolean hasOperators(String toCheck) {
        return toCheck.contains("+") || toCheck.contains("-") || toCheck.contains("*") || toCheck.contains("/") || toCheck.contains("^") || toCheck.contains("(");
    }

    public EvalResult eval() {
        String calc = toEval.replaceAll("\\s+", "");
        List<String> operators = new ArrayList<>();
        VarStore store = Variables.getStorage();
        //Good ole regex
        List<String> ops = Arrays.stream(calc.split("[+\\-*/<>(^]+(?![^(]*\\Q)\\E)")).map(String::trim).collect(Collectors.toList());
        for (String s : ops) {
            operators.add(s);
            int pt = calc.indexOf(s) + s.length();
            //System.out.println(calc.length());
            if (!(pt >= calc.length())) {
                operators.add(String.valueOf(calc.charAt(pt)));
            }
        }
        //First pass, brackets
        operators = new CopyOnWriteArrayList<>(operators);
        for (String s : operators) {
            if (SYM.matcher(s).results().count() > 1) {
                //We are reasonably sure this is another, yet unevaluated snippet
                //As only those can be not split
                //Final test for brackets, then throw this back into this method
                int startPos = s.lastIndexOf("(");
                int closePos = s.lastIndexOf(")");
                if (startPos == -1) {
                    return new Incomplete("Start bracket is missing.");
                } else if (closePos == -1) {
                    return new Incomplete("End bracket is missing.");
                } else if (startPos > closePos) {
                    return new Failure("Close bracket before start bracket: " + calc);
                }
                if (startPos != 0 || closePos != s.length() - 1) {
                    return new Incomplete("Impossible: Brackets not at the start of the split expression? Expression: " + calc);
                }
                //strip off the brackets, then reevaluate
                EvalResult eval = new EvalSnippet(s.substring(1, s.length() - 1)).eval();
                if (eval.isSuccess()) {
                    int index = operators.indexOf(s);
                    //operators.remove(index + 1);
                    operators.remove(index);
                    //operators.remove(index - 1);
                    operators.add(index, String.valueOf(eval.getValue((Object obj) -> {
                        //Cast, we know the answer is double, we stored it there!
                        return (double) obj;
                    })));
                } else {
                    return eval;
                }
            }
        }
        /*//1.5th pass, look for expressions that can be replaced
        for(String s : operators){
            if(s.startsWith("P(")){
                EvalResult eval = evalProbability(s);
                if(eval.isSuccess()){
                    int index = operators.indexOf(s);
                    operators.set(index,eval.getValue((Object obj) -> String.valueOf((double) obj)));
                }else{
                    return eval;
                }
            }
        }*/
        //Second pass, look for ^
        for (String s : operators) {
            if (s.equals("^")) {
                if (operators.indexOf(s) == 0 || operators.indexOf(s) == operators.size() - 1) {
                    return new Incomplete("Exponent operator incomplete, missing either exponent or base. Expression:" + calc);
                } else {
                    try {
                        int index = operators.indexOf(s);
                        String base = operators.get(index - 1);
                        String exp = operators.get(index + 1);
                        double aBase;
                        double aExp;
                        if (needsSubstitution(base)) {
                            if (!store.hasVariable(base)) {
                                return new Failure("Unable to find variable " + base);
                            } else {
                                aBase = store.getVariable(base);
                            }
                        } else {
                            aBase = Double.parseDouble(base);
                        }
                        if (needsSubstitution(exp)) {
                            if (!store.hasVariable(exp)) {
                                return new Failure("Unable to find variable " + base);
                            } else {
                                aExp = store.getVariable(exp);
                            }
                        } else {
                            aExp = Double.parseDouble(exp);
                        }
                        operators.remove(index + 1);
                        operators.remove(index);
                        operators.remove(index - 1);
                        operators.add(index - 1, String.valueOf(Math.pow(aBase, aExp)));
                    } catch (NumberFormatException nfe) {
                        return new Failure("Unable to parse number. Expression: " + calc);
                    }
                }
            }
        }
        for (String s : operators) {
            if (s.equals("*") || s.equals("/")) {
                int index = operators.indexOf(s);
                if (index == 0 || index == operators.size() - 1) {
                    return new Incomplete("Multiplication or division incomplete, missing an argument. Expression: " + calc);
                } else {
                    try {
                        String base = operators.get(index - 1);
                        String exp = operators.get(index + 1);
                        double aBase;
                        double aExp;
                        if (needsSubstitution(base)) {
                            if (!store.hasVariable(base)) {
                                return new Failure("Unable to find variable " + base);
                            } else {
                                aBase = store.getVariable(base);
                            }
                        } else {
                            aBase = Double.parseDouble(base);
                        }
                        if (needsSubstitution(exp)) {
                            if (!store.hasVariable(exp)) {
                                return new Failure("Unable to find variable " + base);
                            } else {
                                aExp = store.getVariable(exp);
                            }
                        } else {
                            aExp = Double.parseDouble(exp);
                        }
                        operators.remove(index + 1);
                        operators.remove(index);
                        operators.remove(index - 1);
                        operators.add(index - 1, String.valueOf(s.equals("*") ? aBase * aExp : aBase / aExp));
                    } catch (NumberFormatException nfe) {
                        return new Failure("Unable to parse number. Expression: " + calc);
                    }
                }
            }
        }
        //Final pass, addition and subtraction
        for (String s : operators) {
            if (s.equals("+") || s.equals("-")) {
                int index = operators.indexOf(s);
                if (index == 0 || index == operators.size() - 1) {
                    return new Incomplete("Addition or subtraction incomplete, missing an argument. Expression: " + calc);
                } else {
                    try {
                        String base = operators.get(index - 1);
                        String exp = operators.get(index + 1);
                        double aBase;
                        double aExp;
                        if (needsSubstitution(base)) {
                            if (!store.hasVariable(base)) {
                                return new Failure("Unable to find variable " + base);
                            } else {
                                aBase = store.getVariable(base);
                            }
                        } else {
                            aBase = Double.parseDouble(base);
                        }
                        if (needsSubstitution(exp)) {
                            if (!store.hasVariable(exp)) {
                                return new Failure("Unable to find variable " + base);
                            } else {
                                aExp = store.getVariable(exp);
                            }
                        } else {
                            aExp = Double.parseDouble(exp);
                        }
                        operators.remove(index + 1);
                        operators.remove(index);
                        operators.remove(index - 1);
                        operators.add(index - 1, String.valueOf(s.equals("+") ? aBase + aExp : aBase - aExp));
                    } catch (NumberFormatException nfe) {
                        return new Failure("Unable to parse number. Expression: " + calc);
                    }
                }
            }
        }
        if (operators.size() > 1) {
            return new Failure("Impossible: After all evaluations fail to get a single answer? Operators remaining: " + operators);
        } else {
            return new Success("Successfully calculated " + calc + " with answer " + operators.get(0), Double.parseDouble(operators.get(0)));
        }
    }

    public static boolean needsSubstitution(String check) {
        return check.chars().mapToObj(i -> (char) i).anyMatch(c -> !(c.equals('.') || Character.isDigit(c)));
    }
}