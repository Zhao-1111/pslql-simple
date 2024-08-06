package com.zck.plsql.intermediate.operator.unaryExpr;

import java.util.Arrays;
import java.util.List;

public enum UnaryExprOperator {
    NEGATIVE("-"),
    POSITIVE("+");

    private final List<String> operatorStrings;

    UnaryExprOperator(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    public List<String> getOperatorStrings() {
        return operatorStrings;
    }

    public static UnaryExprOperator fromString(String input) {
        for (UnaryExprOperator element : UnaryExprOperator.values()) {
            if (element.operatorStrings.contains(input.toUpperCase())) {
                return element;
            }
        }
        throw new IllegalArgumentException("No enum constant associated with input string: " + input);
    }
}
