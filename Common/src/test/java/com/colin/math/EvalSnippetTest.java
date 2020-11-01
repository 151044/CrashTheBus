package com.colin.math;

import com.colin.math.personal.EvalResult;
import com.colin.math.personal.EvalSnippet;
import junit.framework.TestCase;

import java.util.function.Function;

public class EvalSnippetTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testEval() {
        EvalSnippet snip = new EvalSnippet("7 + 8");
        Function<Object,Double> func = (obj) -> (double) obj;
        EvalResult result = snip.eval();
        System.out.println(result.output());
        assertEquals(15.0,result.getValue(func));
        System.out.println("7 + 8 passed.");
        snip = new EvalSnippet("7 * 8");
        result = snip.eval();
        System.out.println(result.output());
        assertEquals(56.0,result.getValue(func));
        System.out.println("7 * 8 passed.");
        snip = new EvalSnippet("7 + (8 * 3)");
        result = snip.eval();
        System.out.println(result.output());
        assertEquals(31.0,result.getValue(func));
        System.out.println("7 + (8 * 3) passed.");
        snip = new EvalSnippet("7 + (8 ^ 3)");
        result = snip.eval();
        System.out.println(result.output());
        assertEquals(519.0,result.getValue(func));
        System.out.println("7 + (8 ^ 3) passed.");
        snip = new EvalSnippet("7 + (8 ^ (3 * 2))");
        result = snip.eval();
        System.out.println(result.output());
        assertEquals(262151.0,result.getValue(func));
        System.out.println("7 + (8 ^ (3 * 2)) passed.");
    }
}