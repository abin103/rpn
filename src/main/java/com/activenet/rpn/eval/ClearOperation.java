package com.activenet.rpn.eval;

import com.activenet.rpn.expression.CalExpression;
import com.activenet.rpn.expression.Operators;

/**
 * UndoOperation
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-26 9:55
 */
public class ClearOperation implements Evalable {
    private CalExpression expression;
    private static final String OP_NAME = Operators.CLEAR.getSymbol();

    public ClearOperation(CalExpression calExpression) {
        this.expression = calExpression;
    }

    public void eval() {

        clear();
        expression.eval();
    }

    public void clear() {
        expression.getExpStack().clear();
        expression.getLastOpreations().clear();
    }

}
