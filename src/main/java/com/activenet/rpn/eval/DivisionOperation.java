package com.activenet.rpn.eval;

import com.activenet.rpn.expression.CalExpression;
import com.activenet.rpn.expression.Operators;

import java.math.BigDecimal;

/**
 * DivisionOperation
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-26 9:57
 */
public class DivisionOperation implements Evalable {
    private CalExpression expression;
    private static final String OP_NAME = Operators.DIVI.getSymbol();

    public DivisionOperation(CalExpression calExpression) {
        this.expression = calExpression;
    }

    public void eval() {
        BigDecimal[] nums = expression.popNums(OP_NAME);
        BigDecimal value = nums[0].divide(nums[1], 15, BigDecimal.ROUND_HALF_EVEN);

        expression.push(value);
        expression.eval();
    }
}
