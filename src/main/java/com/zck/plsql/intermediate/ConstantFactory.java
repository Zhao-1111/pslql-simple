package com.zck.plsql.intermediate;

import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression.BooleanValue;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression.NullValue;

import java.math.BigDecimal;

/**
 * 根据类型创建对应常量
 */
public class ConstantFactory {
    public static ConstantExpression createConstant(Type type) {
        ConstantExpression res = new ConstantExpression();
        res.setType(type);
        switch (type){
            case BOOLEAN:
                res.setValue(BooleanValue.NULL);
                break;
            case PLS_INTEGER:
            case LONG:
            case BINARY_DOUBLE:
            case NUMBER:
            case CHAR:
            case VARCHAR2:
                res.setValue(NullValue.NULL);
                break;
            default:
                break;
        }
        return res;
    }
}
