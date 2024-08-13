package com.zck.plsql.syntax.expression.constantExpression;

import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.intermediate.operator.OperatorInterface;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.syntax.expression.Expression;

import java.math.BigDecimal;

public class ConstantExpression extends Expression {
    private Object value;

    /**
     * 获取常量值
     */
    public Object getConstValue() {
        return value;
    }

    /**
     * 设置常量值，可以进行类型转换
     * 用以解析时置入值
     */
    public void setConstValue(Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof BigDecimal) {
            type = Type.NUMBER;
        } else if (value instanceof String) {
            type = Type.VARCHAR2;
        } else if (value == BooleanValue.TRUE || value == BooleanValue.FALSE) {
            type = Type.BOOLEAN;
        }
        this.value = value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void storeValue(Object value) {
        if (value instanceof ConstantExpression) {
            this.value = ((ConstantExpression)value).getConstValue();
        }
        this.value = value;
    }

    public <E extends Enum<E> & OperatorInterface> void calculationValue(ConstantExpression left,
            ConstantExpression right, E op) {
        if (value == null) {
            return;
        }
        this.value = value;
    }

    @Override
    public Object execute(InterpreterContext ctx) throws Exception {
        return this;
    }

    public enum BooleanValue {
        TRUE, FALSE, NULL
    }
    public enum NullValue {
        NULL
    }
}
