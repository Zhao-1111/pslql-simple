package com.zck.plsql.intermediate.operator;

import java.util.Arrays;
import java.util.List;

public enum RelationalExprOperator {

    EQUAL("="),
    NOTEQUAL("!=", "<>", "^=", "~="),
    LESS("<"),
    LESSEQUAL("<="),
    GREATER(">"),
    GREATEREQUAL(">=");

    private final List<String> operatorStrings;

    RelationalExprOperator(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    public List<String> getOperatorStrings() {
        return operatorStrings;
    }

    public static RelationalExprOperator fromString(String input) {
        for (RelationalExprOperator element : RelationalExprOperator.values()) {
            if (element.operatorStrings.contains(input.toUpperCase())) {
                return element;
            }
        }
        throw new IllegalArgumentException("No enum constant associated with input string: " + input);
    }

}
