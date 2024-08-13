package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.ConstantFactory;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression.BooleanValue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum RelationalExprOperator implements OperatorInterface {

    EQUAL("=") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            if (left.getConstValue() instanceof BigDecimal) {
                BigDecimal v1 = (BigDecimal) left.getConstValue();
                BigDecimal v2 = (BigDecimal) right.getConstValue();
                int comparisonResult = v1.compareTo(v2);
                if (comparisonResult == 0) {
                    res.storeValue(BooleanValue.TRUE);
                } else {
                    res.storeValue(BooleanValue.FALSE);
                }
            }
            return res;
        }
    },
    NOTEQUAL("!=", "<>", "^=", "~=") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            if (left.getConstValue() instanceof BigDecimal) {
                BigDecimal v1 = (BigDecimal) left.getConstValue();
                BigDecimal v2 = (BigDecimal) right.getConstValue();
                int comparisonResult = v1.compareTo(v2);
                if (comparisonResult != 0) {
                    res.storeValue(BooleanValue.TRUE);
                } else {
                    res.storeValue(BooleanValue.FALSE);
                }
            }
            return res;
        }
    },
    LESS("<") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            if (left.getConstValue() instanceof BigDecimal) {
                BigDecimal v1 = (BigDecimal) left.getConstValue();
                BigDecimal v2 = (BigDecimal) right.getConstValue();
                int comparisonResult = v1.compareTo(v2);
                if (comparisonResult < 0) {
                    res.storeValue(BooleanValue.TRUE);
                } else {
                    res.storeValue(BooleanValue.FALSE);
                }
            }
            return res;
        }
    },
    LESSEQUAL("<=") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            if (left.getConstValue() instanceof BigDecimal) {
                BigDecimal v1 = (BigDecimal) left.getConstValue();
                BigDecimal v2 = (BigDecimal) right.getConstValue();
                int comparisonResult = v1.compareTo(v2);
                if (comparisonResult <= 0) {
                    res.storeValue(BooleanValue.TRUE);
                } else {
                    res.storeValue(BooleanValue.FALSE);
                }
            }
            return res;
        }
    },
    GREATER(">") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            if (left.getConstValue() instanceof BigDecimal) {
                BigDecimal v1 = (BigDecimal) left.getConstValue();
                BigDecimal v2 = (BigDecimal) right.getConstValue();
                int comparisonResult = v1.compareTo(v2);
                if (comparisonResult < 0) {
                    res.storeValue(BooleanValue.TRUE);
                } else {
                    res.storeValue(BooleanValue.FALSE);
                }
            }
            return res;
        }
    },
    GREATEREQUAL(">=") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            if (left.getConstValue() instanceof BigDecimal) {
                BigDecimal v1 = (BigDecimal) left.getConstValue();
                BigDecimal v2 = (BigDecimal) right.getConstValue();
                int comparisonResult = v1.compareTo(v2);
                if (comparisonResult >= 0) {
                    res.storeValue(BooleanValue.TRUE);
                } else {
                    res.storeValue(BooleanValue.FALSE);
                }
            }
            return res;
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
