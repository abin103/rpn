package com.activenet.rpn.eval;

import com.activenet.rpn.expression.CalExpression;
import com.activenet.rpn.expression.Operators;

import java.math.BigDecimal;

/**
 * MultOperation
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-26 9:56
 */
public class MultOperation implements Evalable {
    private CalExpression expression;
    private static final String OP_NAME = Operators.MULT.getSymbol();

    public MultOperation(CalExpression calExpression) {
        this.expression = calExpression;
    }

    public void eval() {
        BigDecimal[] nums = expression.popNums(OP_NAME);
        BigDecimal value = nums[0].multiply(nums[1]);

        expression.push(value);
        expression.eval();
    }
}
