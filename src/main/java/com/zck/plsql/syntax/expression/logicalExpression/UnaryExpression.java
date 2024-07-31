package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.intermediate.operator.UnaryExprOperator;
import com.zck.plsql.syntax.expression.Expression;

public class UnaryExpression extends Expression {
    Expression expression;
    UnaryExprOperator unaryExprOperator;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public UnaryExprOperator getUnaryExprOperator() {
        return unaryExprOperator;
    }

    public void setUnaryExprOperator(UnaryExprOperator unaryExprOperator) {
        this.unaryExprOperator = unaryExprOperator;
    }
}
