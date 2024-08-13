package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.ConstantFactory;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression.BooleanValue;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum AndExprOperator implements OperatorInterface {
    AND("AND") {
        /**
         * 	1.	TRUE AND TRUE: 结果为 TRUE。
         * 	2.	TRUE AND FALSE: 结果为 FALSE。
         * 	3.	TRUE AND NULL: 结果为 NULL。
         * 	4.	FALSE AND TRUE: 结果为 FALSE。
         * 	5.	FALSE AND FALSE: 结果为 FALSE。
         * 	6.	FALSE AND NULL: 结果为 FALSE。
         * 	7.	NULL AND TRUE: 结果为 NULL。
         * 	8.	NULL AND FALSE: 结果为 FALSE。
         * 	9.	NULL AND NULL: 结果为 NULL。
         */
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            ConstantExpression res = ConstantFactory.createConstant(expectedType);
            if (left.getConstValue() == BooleanValue.FALSE || right.getConstValue() == BooleanValue.FALSE) {
                res.setValue(BooleanValue.FALSE);
                return res;
            }
            if (left.getConstValue() == BooleanValue.TRUE && right.getConstValue() == BooleanValue.TRUE) {
                res.setValue(BooleanValue.TRUE);
                return res;
            } else {
                return res;
            }
        }
    };

    private static final EnumMap<AndExprOperator, TypeTransition> operatorMap
            = OperatorInterface.initializeOperatorMap(AndExprOperator.class);

    private final List<String> operatorStrings;

    @Override
    public EnumMap<AndExprOperator, TypeTransition> getOperatorMap() {
        return operatorMap;
    }

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
}
