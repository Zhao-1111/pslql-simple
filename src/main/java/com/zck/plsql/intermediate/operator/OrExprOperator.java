package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.type.TypeTransition;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum OrExprOperator implements OperatorInterface{
    OR("OR");

    private static final EnumMap<OrExprOperator, TypeTransition> operatorMap
            = OperatorInterface.initializeOperatorMap(OrExprOperator.class);

    private final List<String> operatorStrings;

    @Override
    public EnumMap<OrExprOperator, TypeTransition> getOperatorMap() {
        return operatorMap;
    }

    OrExprOperator(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    @Override
    public List<String> getOperatorStrings() {

        return operatorStrings;
    }

    public static AndExprOperator fromString(String input) {
        return OperatorInterface.fromString(input, AndExprOperator.class);
    }
}
