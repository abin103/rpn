package com.activenet.rpn.expression;

/**
 * Operators
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-26 10:04
 */
public enum Operators {
    ADD("+"), SUB("-"), MULT("*"), DIVI("/"), UNDO("undo"), CLEAR("clear");
    private final String symbol;

    private Operators(String symbol) {
        this.symbol = symbol;
    }

    public final String getSymbol() {
        return symbol;
    }

    public static String getAllOperators() {
        StringBuilder sb = new StringBuilder();
        for (Operators op : Operators.values()) {
            sb.append(op.getSymbol()).append("|");
        }
        return sb.toString();
    }

}
