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

public enum AssignOperator implements OperatorInterface {
    ASSIGN("ASSIGN") {
        @Override
        public ConstantExpression apply(ConstantExpression left, ConstantExpression right, Type expectedType) {
            left.setType(expectedType);
            left.storeValue(right);
            return left;
        }
    };

    private static final EnumMap<AssignOperator, TypeTransition> operatorMap
            = OperatorInterface.initializeOperatorMap(AssignOperator.class);

    private final List<String> operatorStrings;

    AssignOperator(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    @Override
    public List<String> getOperatorStrings() {
        return operatorStrings;
    }

    @Override
    public EnumMap<AssignOperator, TypeTransition> getOperatorMap() {
        return operatorMap;
    }

    public static AssignOperator fromString(String input) {
        return OperatorInterface.fromString(input, AssignOperator.class);
    }
}
