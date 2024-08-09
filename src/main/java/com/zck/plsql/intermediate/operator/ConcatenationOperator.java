package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeTransition;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum ConcatenationOperator implements OperatorInterface {
    PLUS("+"),
    MINUS("-"),
    ASTERISK("*"),
    SOLIDUS("/"),
    MOD("MOD"),
    BARBAR("||");
    
    private static final EnumMap<ConcatenationOperator, TypeTransition> operatorMap
            = OperatorInterface.initializeOperatorMap(ConcatenationOperator.class);

    private final List<String> operatorStrings;

    ConcatenationOperator(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    @Override
    public List<String> getOperatorStrings() {

        return operatorStrings;
    }

    public static ConcatenationOperator fromString(String input) {
        return OperatorInterface.fromString(input, ConcatenationOperator.class);
    }

    public static Boolean checkType(Type left, Type right, ConcatenationOperator op) throws Exception {
        return OperatorInterface.checkType(left, right, op, operatorMap);
    }
}
