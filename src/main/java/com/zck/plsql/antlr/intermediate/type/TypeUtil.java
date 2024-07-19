package com.zck.plsql.antlr.intermediate.type;

import com.zck.plsql.antlr.syntax.expression.Expression;

import java.util.HashMap;
import java.util.Map;

public class TypeUtil {
    private static Map<String, Type> map = new HashMap<String, Type>() {{
        put("BOOLEAN", Type.BOOL);
        put("INT", Type.INT);
        put("LONG", Type.LONG);
        put("DOUBLE", Type.DOUBLE);
        put("NUMBER", Type.NUMBER);
        put("VARCHAR", Type.VARCHAR);
        put("CHAR", Type.CHAR);
        put("DATA", Type.DATA);
    }};

    public static Type getType(String type) {
        return map.get(type.toUpperCase());
    }

    public static boolean checkType(Expression left, Expression right) {
        TypeSpec leftType = left.getType();
        TypeSpec rightType = null;
        if (right == null || right.getType() == null) {
            return true;
        } else {
            rightType = right.getType();

        }
        boolean canCast = false;
        if (leftType.getType() == rightType.getType()) {
            return true;
        } else {
            return true;
        }
    }
}
