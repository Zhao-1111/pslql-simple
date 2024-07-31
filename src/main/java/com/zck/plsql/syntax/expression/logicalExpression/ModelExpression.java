package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.syntax.expression.Expression;

/**
 * model_expression
 * : unary_expression ('[' model_expression_element ']')?
 * ;
 */
public class ModelExpression extends Expression {
    Expression unaryExpr;

    public Expression getUnaryExpr() {
        return unaryExpr;
    }

    public void setUnaryExpr(Expression unaryExpr) {
        this.unaryExpr = unaryExpr;
    }
}
