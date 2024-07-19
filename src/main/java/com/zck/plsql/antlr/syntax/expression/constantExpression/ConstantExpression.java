package com.zck.plsql.antlr.syntax.expression.constantExpression;

import com.zck.plsql.antlr.intermediate.type.Type;
import com.zck.plsql.antlr.intermediate.type.TypeSpec;
import com.zck.plsql.antlr.syntax.expression.Expression;

import java.math.BigDecimal;

public class ConstantExpression extends Expression {
    protected Object value;

    /**
     * 获取常量值
     *
     * @return
     */
    public Object getConstValue() {
        return null;
    }

    ;

    /**
     * 设置常量值，可以进行类型转换
     */
    public void setConstValue(Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof BigDecimal) {
            type = new TypeSpec(Type.DOUBLE);
        }
        else if (value instanceof String) {
            type = new TypeSpec(Type.VARCHAR);
        }
        this.value = value;
    }

    ;
}
