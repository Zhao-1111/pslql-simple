package com.zck.plsql.intermediate;

import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;

/**
 * 根据类型创建对应常量
 */
public class ConstantFactory {
    public static ConstantExpression createConstant(Type type) {
        ConstantExpression res = new ConstantExpression();
        res.setType(type);
        return res;
    }
}
