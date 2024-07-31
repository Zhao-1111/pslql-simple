package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.intermediate.operator.ConcatenationOperator;
import com.zck.plsql.syntax.expression.Expression;

/**
 * concatenation
 *     : model_expression (AT (LOCAL | TIME ZONE concatenation) | interval_expression)? (
 *         ON OVERFLOW (TRUNCATE | ERROR)
 *     )?
 *     | concatenation op = DOUBLE_ASTERISK concatenation
 *     | concatenation op = (ASTERISK | SOLIDUS | MOD) concatenation
 *     | concatenation op = (PLUS_SIGN | MINUS_SIGN) concatenation
 *     | concatenation BAR BAR concatenation
 *     | concatenation COLLATE column_collation_name
 *     ;
 */
public class Concatenation extends Expression {
    Expression left, right;
    ConcatenationOperator operator;

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

    public ConcatenationOperator getOperator() {
        return operator;
    }

    public void setOperator(ConcatenationOperator operator) {
        this.operator = operator;
    }
}
