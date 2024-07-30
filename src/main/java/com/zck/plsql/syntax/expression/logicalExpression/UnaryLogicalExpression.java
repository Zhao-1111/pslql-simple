package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.syntax.expression.Expression;

/**
 * unary_logical_expression
 *     : NOT? multiset_expression unary_logical_operation?
 *     ;
 */
public class UnaryLogicalExpression extends Expression {
    private boolean not = false;
    private MultisetExpression multisetExpression;

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    public MultisetExpression getMultisetExpression() {
        return multisetExpression;
    }

    public void setMultisetExpression(MultisetExpression multisetExpreesion) {
        this.multisetExpression = multisetExpreesion;
    }
}
