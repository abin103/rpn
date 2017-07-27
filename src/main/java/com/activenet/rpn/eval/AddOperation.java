package com.activenet.rpn.eval;

import com.activenet.rpn.expression.CalExpression;
import com.activenet.rpn.expression.Operators;

import java.math.BigDecimal;

/**
 * Operation
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-25 22:42
 */
public class AddOperation implements Evalable {
    private CalExpression expression;
    private static final String OP_NAME = Operators.ADD.getSymbol();

    public AddOperation(CalExpression calExpression) {
        this.expression = calExpression;
    }

    public void eval() {

        BigDecimal[] nums = expression.popNums(OP_NAME);
        BigDecimal sum = nums[1].add(nums[0]);

        expression.push(sum);
        expression.eval();
    }

}
