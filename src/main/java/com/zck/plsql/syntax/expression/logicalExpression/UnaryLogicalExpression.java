package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.syntax.expression.Expression;

/**
 * unary_logical_expression
 *     : NOT? multiset_expression unary_logical_operation?
 *     ;
 */
public class UnaryLogicalExpression extends LogicalUnaryExpression {
    private boolean not = false;

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }
}
