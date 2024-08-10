package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum UnaryExprOperator implements OperatorInterface {
    NEGATIVE("-") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    POSITIVE("+") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    };

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

    @Override
    public EnumMap<UnaryExprOperator, TypeTransition> getOperatorMap() {
        return operatorMap;
    }

    public static RelationalExprOperator fromString(String input) {
        return OperatorInterface.fromString(input, RelationalExprOperator.class);
    }

}
