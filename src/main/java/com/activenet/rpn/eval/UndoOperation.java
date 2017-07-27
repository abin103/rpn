package com.activenet.rpn.eval;

import com.activenet.rpn.expression.CalExpression;
import com.activenet.rpn.expression.Expression;
import com.activenet.rpn.expression.Operators;

import java.math.BigDecimal;
import java.util.List;

/**
 * UndoOperation
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-26 9:55
 */
public class UndoOperation implements Evalable {
    private CalExpression expression;
    private static final String OP_NAME = Operators.UNDO.getSymbol();

    public UndoOperation(CalExpression calExpression) {
        this.expression = calExpression;
    }

    public void eval() {
        // expression.popOne();
        undo();
        expression.eval();
    }

    public void undo() {
        List<Expression.LastOpreation> list = expression.getLastOpreations();
        if (list.size() > 0) {
            Expression.LastOpreation lastOpreation = list.get(list.size() - 1);
            if ("push".equals(lastOpreation.getOpreatFlag())) {
                expression.getExpStack().pop();
            } else {
                String flag = lastOpreation.getOpreatFlag();
                // 先弹出结果
                BigDecimal val = expression.getExpStack().pop();
                expression.getExpStack().push(lastOpreation.getNum1());
                //expression.getExpStack().push(lastOpreation.getNum2());
            }

            list.remove(lastOpreation);
        }

    }
}
