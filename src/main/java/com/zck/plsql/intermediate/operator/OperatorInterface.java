package com.zck.plsql.intermediate.operator;

import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.util.JsonUtil;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

public interface OperatorInterface {
    String separator = "/";
    // json文件存放位置
    String path = "operator" + separator;

    List<String> getOperatorStrings();

    <E extends Enum<E> & OperatorInterface> EnumMap<E, TypeTransition> getOperatorMap();

    static <E extends Enum<E> & OperatorInterface> EnumMap<E, TypeTransition> initializeOperatorMap(
            Class<E> enumClass) {
        EnumMap<E, TypeTransition> operatorMap = new EnumMap<>(enumClass);
        for (E value : enumClass.getEnumConstants()) {
            try {
                operatorMap.put(value, JsonUtil.jsonFile2map(
                        path + enumClass.getSimpleName() + separator + value.name() + ".json"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return operatorMap;
    }

    static <E extends Enum<E> & OperatorInterface> E fromString(String input, Class<E> enumClass) {
        for (E element : enumClass.getEnumConstants()) {
            if (element.getOperatorStrings().contains(input.toUpperCase())) {
                return element;
            }
        }
        throw new IllegalArgumentException("No enum constant associated with input string: " + input);
    }

    static <E extends Enum<E> & OperatorInterface> boolean checkType(Type left, Type right, E op) throws Exception {
        if (!op.getOperatorMap().containsKey(op)) {
            throw new Exception("operator error");
        }
        TypeTransition typeTransition = op.getOperatorMap().get(op);
        if (typeTransition == null) {
            throw new Exception("operator error");
        }
        if (typeTransition.containsKey(left) && typeTransition.get(left).containsKey(right)) {
            switch (typeTransition.get(left).get(right)) {
                case ERROR:
                    throw new Exception("type error");
                case NONSUPPORT:
                    throw new Exception("Not supported");
                default:
                    return true;
            }
        }
        return false;
    }

    static <E extends Enum<E> & OperatorInterface> boolean checkType(Type type, E op) throws Exception {
        return checkType(type, Type.NULLTYPE, op);
    }

    static <E extends Enum<E> & OperatorInterface> Type castType(Type left, Type right, E op) throws Exception {
        if (left == null || right == null || op == null) {
            return Type.ERROR;
        }
        EnumMap<E, TypeTransition> operatorMap = op.getOperatorMap();
        if (checkType(left, right, op)) {
            return operatorMap.get(op).get(left).get(right);
        }
        return Type.ERROR;
    }

    static <E extends Enum<E> & OperatorInterface> Type castType(Type type, E op) throws Exception {
        return castType(type, Type.NULLTYPE, op);
    }

}