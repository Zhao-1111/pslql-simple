package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.ConstantFactory;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum ConcatenationOperator implements OperatorInterface {
    PLUS("+") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            BigDecimal resValue = ((BigDecimal) left.getConstValue()).add((BigDecimal) right.getConstValue());
            res.storeValue(resValue);
            return res;
        }
    },
    MINUS("-") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            BigDecimal resValue = ((BigDecimal) left.getConstValue()).subtract((BigDecimal) right.getConstValue());
            res.storeValue(resValue);
            return res;
        }
    },
    ASTERISK("*") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            BigDecimal resValue = ((BigDecimal) left.getConstValue()).multiply((BigDecimal) right.getConstValue());
            res.storeValue(resValue);
            return res;
        }
    },
    SOLIDUS("/") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            BigDecimal resValue = ((BigDecimal) left.getConstValue()).divide((BigDecimal) right.getConstValue());
            res.storeValue(resValue);
            return res;
        }
    },
    MOD("MOD") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            BigDecimal resValue = ((BigDecimal) left.getConstValue()).remainder((BigDecimal) right.getConstValue());
            res.storeValue(resValue);
            return res;
        }
    },
    BARBAR("||") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            String resValue = left.getConstValue() + ((String) right.getConstValue());
            res.storeValue(resValue);
            return res;
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
