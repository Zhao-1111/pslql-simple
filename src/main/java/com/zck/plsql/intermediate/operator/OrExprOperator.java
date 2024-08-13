package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.ConstantFactory;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression.BooleanValue;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum OrExprOperator implements OperatorInterface {
    OR("OR") {
        /**
         * 	1.	TRUE OR TRUE: 结果为 TRUE。
         * 	2.	TRUE OR FALSE: 结果为 TRUE。
         * 	3.	TRUE OR NULL: 结果为 TRUE。
         * 	4.	FALSE OR TRUE: 结果为 TRUE。
         * 	5.	FALSE OR FALSE: 结果为 FALSE。
         * 	6.	FALSE OR NULL: 结果为 NULL。
         * 	7.	NULL OR TRUE: 结果为 TRUE。
         * 	8.	NULL OR FALSE: 结果为 NULL。
         * 	9.	NULL OR NULL: 结果为 NULL。
         */
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right,Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            if (left.getConstValue() == BooleanValue.TRUE || right.getConstValue() == BooleanValue.TRUE) {
                res.setValue(BooleanValue.TRUE);
                return res;
            }
            if (left.getConstValue() == BooleanValue.FALSE && right.getConstValue() == BooleanValue.FALSE) {
                res.setValue(BooleanValue.FALSE);
                return res;
            } else {
                return res;
            }
        }
    };

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
