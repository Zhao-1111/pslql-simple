package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeTransition;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum UnaryExprOperator implements OperatorInterface{
    NEGATIVE("-"),
    POSITIVE("+");

    private static final EnumMap<UnaryExprOperator, TypeTransition> operatorMap
            = OperatorInterface.initializeOperatorMap(UnaryExprOperator.class);

    private final List<String> operatorStrings;

    UnaryExprOperator(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    @Override
    public List<String> getOperatorStrings() {

        return operatorStrings;
    }

    public static RelationalExprOperator fromString(String input) {
        return OperatorInterface.fromString(input, RelationalExprOperator.class);
    }

    public static Boolean checkType(Type left, Type right, UnaryExprOperator op) throws Exception {
        return OperatorInterface.checkType(left, right, op, operatorMap);
    }
    // 一元表达式，认为是特殊的二元表达式，第二个操作符号，认为其为Type.NULLTYPE
    public static Boolean checkType(Type type, UnaryExprOperator op) throws Exception {
        return checkType(type, Type.NULLTYPE, op);
    }
}
