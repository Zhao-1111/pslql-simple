package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.TypeTansition;
import com.zck.plsql.intermediate.type.Type;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum ConcatenationOperator {
    PLUS("+"),
    MINUS("-"),
    ASTERISK("*"),
    SOLIDUS("/"),
    MOD("MOD"),
    BARBAR("||");

    private static EnumMap<ConcatenationOperator, TypeTansition> operatorMap
            = new EnumMap<ConcatenationOperator, TypeTansition>(ConcatenationOperator.class) {{
        for (ConcatenationOperator value : ConcatenationOperator.values()) {
            put(value, new TypeTansition(Type.class));
        }
    }};

    private final List<String> operatorStrings;

    ConcatenationOperator(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    public List<String> getOperatorStrings() {
        return operatorStrings;
    }

    public static ConcatenationOperator fromString(String input) {
        for (ConcatenationOperator element : ConcatenationOperator.values()) {
            if (element.operatorStrings.contains(input.toUpperCase())) {
                return element;
            }
        }
        throw new IllegalArgumentException("No enum constant associated with input string: " + input);
    }

}
