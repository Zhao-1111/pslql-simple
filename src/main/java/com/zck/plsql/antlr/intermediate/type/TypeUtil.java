package com.zck.plsql.antlr.intermediate.type;

import com.zck.plsql.antlr.syntax.expression.Expression;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TypeUtil {
    private static Map<String, Type> map = new HashMap<String, Type>() {{
        put("BOOLEAN", Type.BOOL);
        put("INT", Type.INT);
        put("LONG", Type.LONG);
        put("DOUBLE", Type.DOUBLE);
        put("NUMBER", Type.NUMBER);
        put("VARCHAR", Type.VARCHAR);
        put("CHAR", Type.CHAR);
        put("DATE", Type.DATE);
    }};

    private static Set<Type> boolSet = new HashSet<Type>() {{
        add(Type.BOOL);
    }};

    private static Set<Type> intSet = new HashSet<Type>() {{
        add(Type.INT);
        add(Type.LONG);
        add(Type.DOUBLE);
        add(Type.NUMBER);
    }};
    private static Set<Type> longSet = intSet;
    private static Set<Type> doubleSet = intSet;
    private static Set<Type> numberSet = intSet;

    private static Set<Type> varcharSet = new HashSet<Type>() {{
        add(Type.VARCHAR);
        add(Type.CHAR);
    }};
    private static Set<Type> charSet = varcharSet;

    private static Set<Type> dateSet = new HashSet<Type>() {{
        add(Type.DATE);
    }};

    private static Map<Type, Set<Type>> typeMatchMap = new HashMap<Type, Set<Type>>() {{
        put(Type.BOOL, boolSet);
        put(Type.INT, intSet);
        put(Type.LONG, longSet);
        put(Type.DOUBLE, doubleSet);
        put(Type.NUMBER, numberSet);
        put(Type.VARCHAR, varcharSet);
        put(Type.CHAR, charSet);
        put(Type.DATE, dateSet);
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
        return typeMatchMap.get(leftType.getType()).contains(rightType.getType());
    }
}
