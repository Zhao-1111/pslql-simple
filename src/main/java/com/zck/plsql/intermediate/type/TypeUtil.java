package com.zck.plsql.intermediate.type;

import com.zck.plsql.syntax.expression.Expression;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TypeUtil {
    private static Set<Type> boolSet = new HashSet<Type>() {{
        add(Type.BOOLEAN);
        add(Type.NULL);
    }};

    private static Set<Type> intSet = new HashSet<Type>() {{
        add(Type.BINARY_INTEGER);
        add(Type.PLS_INTEGER);
        add(Type.BINARY_DOUBLE);
        add(Type.BINARY_FLOAT);
        add(Type.NUMBER);
        add(Type.NULL);
    }};
    private static Set<Type> floatSet = intSet;
    private static Set<Type> doubleSet = intSet;
    private static Set<Type> numberSet = intSet;

    private static Set<Type> varcharSet = new HashSet<Type>() {{
        add(Type.VARCHAR2);
        add(Type.CHAR);
        add(Type.NULL);
    }};
    private static Set<Type> charSet = varcharSet;

    private static Set<Type> dateSet = new HashSet<Type>() {{
        add(Type.DATE);
        add(Type.NULL);
    }};

    private static Map<Type, Set<Type>> typeMatchMap = new HashMap<Type, Set<Type>>() {{
        put(Type.BOOLEAN, boolSet);
        put(Type.PLS_INTEGER, intSet);
        put(Type.BINARY_FLOAT, floatSet);
        put(Type.BINARY_DOUBLE, doubleSet);
        put(Type.NUMBER, numberSet);
        put(Type.VARCHAR2, varcharSet);
        put(Type.CHAR, charSet);
        put(Type.DATE, dateSet);
    }};

    public static Type getType(String type) {
        return Type.fromString(type);
    }

    public static boolean checkType(Expression left, Expression right) {
        TypeSpec leftType = left.getType();
        TypeSpec rightType = null;
        if (right == null || right.getType() == null) {
            return true;
        } else {
            rightType = right.getType();

        }
        return typeMatchMap.get(leftType.getType()).contains(rightType.getType());
    }
}
