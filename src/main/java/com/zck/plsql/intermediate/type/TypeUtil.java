package com.zck.plsql.intermediate.type;

import com.zck.plsql.intermediate.operator.AssignOperator;
import com.zck.plsql.syntax.expression.Expression;

public class TypeUtil {
    public static Type getType(String type) {
        return Type.fromString(type);
    }

    public static boolean checkType(Expression left, Expression right) throws Exception {
        Type leftType = left.getType();
        Type rightType = null;
        if (right == null || right.getType() == null) {
            return false;
        } else {
            rightType = right.getType();
        }
        return AssignOperator.checkType(leftType, rightType, AssignOperator.ASSIGN);
    }
}
