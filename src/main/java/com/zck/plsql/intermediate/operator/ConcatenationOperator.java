package com.zck.plsql.intermediate.operator;

import java.util.Arrays;
import java.util.List;

public enum ConcatenationOperator {
    PLUS("+"),
    MINUS("-"),
    BARBAR("||");

    private final List<String> operatorStrings;

    ConcatenationOperator(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    public List<String> getOperatorStrings() {
        return operatorStrings;
    }

    public static ConcatenationOperator fromString(String input) {
        for (ConcatenationOperator element : ConcatenationOperator.values()) {
            if (element.operatorStrings.contains(input)) {
                return element;
            }
        }
        throw new IllegalArgumentException("No enum constant associated with input string: " + input);
    }

}
