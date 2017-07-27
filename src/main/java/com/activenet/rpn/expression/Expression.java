package com.activenet.rpn.expression;

import com.activenet.rpn.eval.Evalable;
import com.activenet.rpn.exception.ExpressionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Calculate
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-25 18:35
 */
public abstract class Expression implements Evalable {
    final static Logger LOGGER = LoggerFactory.getLogger(Expression.class);
    private Stack<BigDecimal> expStack;

    private static final DecimalFormat SHOWFORMAT = new DecimalFormat("#.##########");
    private static final int DECIMA_SIZE = 15;

    private String line;

    //上次操作记录
    private List<LastOpreation> lastOpreations;

    public abstract void eval();

    public Expression(Stack<BigDecimal> expStack) {
        this.expStack = expStack;
        if (lastOpreations == null) {
            lastOpreations = new ArrayList<>();
        }
    }

    public Stack<BigDecimal> getExpStack() {
        return expStack;
    }

    /**
     * 返回顺序和 压栈顺序相同
     * 
     * @return
     */
    public BigDecimal[] popNums(String operationName) {
        int postion = expStack.size();

        if (postion <= 1) {
            throw new ExpressionException(String.format("operator %s(position: %d): insufficient parameters", operationName, postion));
        }
        BigDecimal a = expStack.pop();
        postion--;
        BigDecimal b = expStack.pop();
        postion--;

        lastOpreations.add(new LastOpreation(operationName, b, a));
        return new BigDecimal[] {b, a};

    }

    private int getPostion() {
        return expStack.size();

    }

    public void push(String num) {
        try {
            BigDecimal bigDecimal = new BigDecimal(num);
            bigDecimal.setScale(DECIMA_SIZE, BigDecimal.ROUND_HALF_UP);
            expStack.push(bigDecimal);

            lastOpreations.add(new LastOpreation("push", bigDecimal, bigDecimal));
        } catch (NumberFormatException e) {
            LOGGER.warn("operator push (input:<{}>):insufficient operands", num);
        }
    }

    public void push(BigDecimal bigDecimal) {
        expStack.push(bigDecimal);
    }

    public void printBuffer() {
        System.out.println(getBufferString());
    }

    public String getBufferString() {
        Stack<BigDecimal> strings = getExpStack();
        StringBuilder sb = new StringBuilder("buffer:");
        for (int i = 0; i < strings.size(); i++) {
            sb.append(" ").append(SHOWFORMAT.format(strings.get(i)));
        }
        return sb.toString();
    }

    public List<LastOpreation> getLastOpreations() {
        return lastOpreations;
    }

    public static class LastOpreation {
        private String opreatFlag;
        private BigDecimal num1;
        private BigDecimal num2;

        public LastOpreation(String opreatFlag, BigDecimal num1, BigDecimal num2) {
            this.opreatFlag = opreatFlag;
            this.num1 = num1;
            this.num2 = num2;
        }

        public String getOpreatFlag() {
            return opreatFlag;
        }

        public void setOpreatFlag(String opreatFlag) {
            this.opreatFlag = opreatFlag;
        }

        public BigDecimal getNum1() {
            return num1;
        }

        public void setNum1(BigDecimal num1) {
            this.num1 = num1;
        }

        public BigDecimal getNum2() {
            return num2;
        }

        public void setNum2(BigDecimal num2) {
            this.num2 = num2;
        }
    }
}
