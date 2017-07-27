package com.activenet.rpn;

import com.activenet.rpn.exception.ExpressionException;
import com.activenet.rpn.expression.CalExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;

/**
 * 逆波兰表示法实现 RPNMain
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-25 11:27
 */
public class RPNMain {

    final static Logger LOGGER = LoggerFactory.getLogger(RPNMain.class);

    private boolean exit = false;

    public RPNMain() {

    }

    public static void main(String[] args) {
        LOGGER.debug("逆波兰表示法实现");
        // 字符表达式栈
        Stack<BigDecimal> expStack = new Stack<BigDecimal>();

        RPNMain prn = new RPNMain();
        CalExpression expression = new CalExpression(expStack);
        // LOGGER.debug("空格分隔");
        LOGGER.debug("请输入数据:");

        Scanner scan = new Scanner(System.in);
        while (!prn.exit) {
            try {

                String line = scan.nextLine();

                if (line == null || "quit".equals(line.trim())) prn.exit = true;
                if (prn.exit) break;

                expression.parsing(line);

            } catch (java.util.NoSuchElementException e) {
                prn.exit = true;
            } catch (ExpressionException e) {
                LOGGER.warn(e.getMessage());
            }
            expression.printBuffer();
        }
        scan.close();

    }

}
