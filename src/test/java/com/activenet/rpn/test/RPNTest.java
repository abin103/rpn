package com.activenet.rpn.test;

import com.activenet.rpn.expression.CalExpression;
import com.activenet.rpn.exception.ExpressionException;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * RPNTest
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-27 9:58
 */
public class RPNTest extends TestCase {

    private Stack<BigDecimal> expStack = new Stack<BigDecimal>();
    private CalExpression expression = new CalExpression(expStack);

    public void testNumResult() {
        String line = "1 2";
        expression.parsing(line);
        assertEquals(expression.getBufferString(), "buffer: 1 2");
    }

    public void testOperationResult() {
        String line = "clear 4 2 3 5 4 + - * /";
        expression.parsing(line);
        assertEquals(expression.getBufferString(), "buffer: -0.3333333333");
    }

    public void testIllegalResult() {
        try {
            String line = "clear 3 -";
            expression.parsing(line);
        } catch (ExpressionException e) {
            e.printStackTrace();
        }
        assertEquals(expression.getBufferString(), "buffer: 3");
    }

    public void testAllResult() {

        String line = "clear 3 4 2 * 3 + 1 -";
        expression.parsing(line);
        assertEquals(expression.getBufferString(), "buffer: 3 10");

        line = "clear 6 3 1 + 5 * undo +";
        expression.parsing(line);
        assertEquals(expression.getBufferString(), "buffer: 10");

        line = "clear 3 4 + clear 1";
        expression.parsing(line);
        assertEquals(expression.getBufferString(), "buffer: 1");

        line = "clear 2 3 undo";
        expression.parsing(line);
        assertEquals(expression.getBufferString(), "buffer: 2");

        line = "clear 6 5 4 3";
        expression.parsing(line);
        line = "undo undo *";
        expression.parsing(line);
        line = "10 *";
        expression.parsing(line);
        line = "undo";
        expression.parsing(line);
        assertEquals(expression.getBufferString(), "buffer: 30");
    }
}
