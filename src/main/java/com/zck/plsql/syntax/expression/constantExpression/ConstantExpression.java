package com.zck.plsql.syntax.expression.constantExpression;

import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeSpec;
import com.zck.plsql.syntax.expression.Expression;

import java.math.BigDecimal;

public class ConstantExpression extends Expression {
    protected Object value;

    /**
     * 获取常量值
     *
     * @return
     */
    public Object getConstValue() {
        return value;
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

    @Override
    public Object execute(InterpreterContext ctx) throws Exception {
        return this;
    }
}
