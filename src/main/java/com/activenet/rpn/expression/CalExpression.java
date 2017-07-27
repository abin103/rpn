package com.activenet.rpn.expression;

import java.math.BigDecimal;
import java.util.Stack;

import com.activenet.rpn.eval.*;

/**
 * CalculateExp
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-25 22:45
 */
public class CalExpression extends Expression {

    public CalExpression(Stack<BigDecimal> expStack) {
        super(expStack);
    }

    @Override
    public void eval() {

        // do noting
    }

    /**
     * 解析表达式
     * 
     * @param line
     */
    public void parsing(String line) {
        assert line != null;
        String[] chars = line.split("\\s");
        String allSymbol = Operators.getAllOperators();

        Evalable evalable = null;
        for (String token : chars) {
            token = token.trim();
            if (!allSymbol.contains(token)) {
                super.push(token);
            } else {
                // 匹配操作符号
                switch (token) {
                    case "+":
                        evalable = new AddOperation(this);
                        break;
                    case "-":
                        evalable = new SubOperation(this);
                        break;
                    case "*":
                        evalable = new MultOperation(this);
                        break;
                    case "/":
                        evalable = new DivisionOperation(this);
                        break;
                    case "undo":
                        evalable = new UndoOperation(this);
                        break;
                    case "clear":
                        evalable = new ClearOperation(this);
                        break;
                }
                if (evalable != null) {
                    // 计算表达式
                    evalable.eval();
                }
            }
        }
        // super.printBuffer();
    }

}
