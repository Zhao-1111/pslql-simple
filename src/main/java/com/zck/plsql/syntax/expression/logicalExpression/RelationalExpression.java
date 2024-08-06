package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.intermediate.operator.relationalExpr.RelationalExprOperator;
import com.zck.plsql.syntax.expression.Expression;

/**
 * rule1
 * relational_expression
 * : relational_expression relational_operator relational_expression
 * | compound_expression
 * ;
 * <p>
 * rule2
 * relational_operator
 * : '='
 * | (NOT_EQUAL_OP | '<' '>' | '!' '=' | '^' '=')
 * | ('<' | '>') '='?
 * ;
 */
public class RelationalExpression extends Expression {

    private RelationalExprOperator exprOperator;
    private Expression left, right;

    public void setExprOperator(RelationalExprOperator exprOperator) {
        this.exprOperator = exprOperator;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }
}
