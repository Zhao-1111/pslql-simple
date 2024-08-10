package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum RelationalExprOperator implements OperatorInterface {

    EQUAL("=") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    NOTEQUAL("!=", "<>", "^=", "~=") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    LESS("<") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    LESSEQUAL("<=") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    GREATER(">") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    GREATEREQUAL(">=") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    };

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
