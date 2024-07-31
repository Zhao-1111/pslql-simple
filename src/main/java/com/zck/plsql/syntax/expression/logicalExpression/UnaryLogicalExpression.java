package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.syntax.expression.Expression;

/**
 * unary_logical_expression
 *     : NOT? multiset_expression unary_logical_operation?
 *     ;
 */
public class UnaryLogicalExpression extends Expression {
    private boolean not = false;
    private Expression multisetExpression;

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    public Expression getMultisetExpression() {
        return multisetExpression;
    }

    public void setMultisetExpression(Expression multisetExpreesion) {
        this.multisetExpression = multisetExpreesion;
    }
}
