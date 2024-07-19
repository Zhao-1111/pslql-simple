package com.zck.plsql.antlr.intermediate.type;

import com.zck.plsql.antlr.syntax.expression.Expression;

public class TypeCheckUtil {
    public static boolean checkType(Expression left, Expression right) {
        TypeSpec leftType = left.getType();
        TypeSpec rightType = null;
        if (right == null || right.getType() == null) {
            return true;
        } else {
            rightType = right.getType();

        }
        return leftType.getType().equals(rightType.getType());
    }
}
