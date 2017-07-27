package com.activenet.rpn.eval;

import com.activenet.rpn.expression.CalExpression;
import com.activenet.rpn.expression.Operators;

import java.math.BigDecimal;

/**
 * SubOpreration
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-25 23:04
 */
public class SubOperation implements Evalable {
    private static final String OP_NAME = Operators.SUB.getSymbol();
    private CalExpression expression;

    public SubOperation(CalExpression calExpression) {
        this.expression = calExpression;
    }

    public void eval() {
        BigDecimal[] nums = expression.popNums(OP_NAME);
        BigDecimal value = nums[0].subtract(nums[1]);

        expression.push(value);
        expression.eval();
    }
}
