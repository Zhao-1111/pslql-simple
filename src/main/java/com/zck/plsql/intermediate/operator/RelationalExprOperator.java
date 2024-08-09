package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.type.TypeTransition;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum RelationalExprOperator implements OperatorInterface {

    EQUAL("="),
    NOTEQUAL("!=", "<>", "^=", "~="),
    LESS("<"),
    LESSEQUAL("<="),
    GREATER(">"),
    GREATEREQUAL(">=");

    private static final EnumMap<RelationalExprOperator, TypeTransition> operatorMap
            = OperatorInterface.initializeOperatorMap(RelationalExprOperator.class);

    private final List<String> operatorStrings;

    RelationalExprOperator(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    @Override
    public List<String> getOperatorStrings() {
        return operatorStrings;
    }

    @Override
    public EnumMap<RelationalExprOperator, TypeTransition> getOperatorMap() {
        return operatorMap;
    }

    public static RelationalExprOperator fromString(String input) {
        return OperatorInterface.fromString(input, RelationalExprOperator.class);
    }



}
