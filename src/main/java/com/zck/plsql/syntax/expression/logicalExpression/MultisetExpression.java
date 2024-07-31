package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.syntax.expression.Expression;

public class MultisetExpression extends Expression {
    private Expression relationalExpression;

    public Expression getRelationalExpression() {
        return relationalExpression;
    }

    public void setRelationalExpression(
            Expression relationalExpression) {
        this.relationalExpression = relationalExpression;
    }
}
