package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeTransition;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum AndExprOperator implements OperatorInterface{
    AND("AND");

    private static EnumMap<AndExprOperator, TypeTransition> operatorMap
            = OperatorInterface.initializeOperatorMap(AndExprOperator.class);

    private final List<String> operatorStrings;

    AndExprOperator(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    @Override
    public List<String> getOperatorStrings() {

        return operatorStrings;
    }

    public static AndExprOperator fromString(String input) {
        return OperatorInterface.fromString(input, AndExprOperator.class);
    }

    public static Boolean checkType(Type left, Type right, AndExprOperator op) throws Exception {
        return OperatorInterface.checkType(left, right, op, operatorMap);
    }

    public static Type castType(Type left, Type right, AndExprOperator op) throws Exception {
        if(checkType(left, right, op)) {
            return operatorMap.get(op).get(left).get(right);
        }
        return Type.ERROR;
    }
}
