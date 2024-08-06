package com.zck.plsql.intermediate.operator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.util.JsonUtil;

import java.util.EnumMap;

public class AssignOperator {
    private static TypeTransition operatorMap;

    static {
        try {
            operatorMap = JsonUtil.json2map("out_put_json/assign.json");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean assignCheckType(Type left, Type right) throws Exception {
        if (operatorMap.containsKey(left) && operatorMap.get(left).containsKey(right)) {
            switch (operatorMap.get(left).get(right)) {
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
}
