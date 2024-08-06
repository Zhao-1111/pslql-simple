package com.zck.plsql.intermediate.operator.concatenation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.util.JsonUtil;

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

    private static EnumMap<ConcatenationOperator, TypeTransition> operatorMap
            = new EnumMap<ConcatenationOperator, TypeTransition>(ConcatenationOperator.class) {{
        for (ConcatenationOperator value : ConcatenationOperator.values()) {
            try {
                put(value, JsonUtil.json2map(value.name()+".json"));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
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
