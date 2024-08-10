package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum ConcatenationOperator implements OperatorInterface {
    PLUS("+"){
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    MINUS("-"){
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    ASTERISK("*"){
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    SOLIDUS("/"){
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    MOD("MOD"){
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    },
    BARBAR("||") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right) {
            return null;
        }
    };

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

    @Override
    public EnumMap<ConcatenationOperator, TypeTransition> getOperatorMap() {
        return operatorMap;
    }

    public static ConcatenationOperator fromString(String input) {
        return OperatorInterface.fromString(input, ConcatenationOperator.class);
    }
}
