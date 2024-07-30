package com.zck.plsql.intermediate.type;

import com.zck.plsql.syntax.expression.variableExpression.VariableExpression;

public class TypeSpec {
    private Type type;

    public TypeSpec(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(VariableExpression variableExpression) {
        this.type = variableExpression.getType().getType();
    }

    public void setType(Type type) {
        this.type = type;
    }
}
