package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.intermediate.SymTab;
import com.zck.plsql.intermediate.operator.AndExprOperator;
import com.zck.plsql.intermediate.type.TypeUtil;
import com.zck.plsql.syntax.expression.Expression;

public class AndExpression extends Expression {
    private Expression left, right;

    private final AndExprOperator andExprOperator = AndExprOperator.AND;

    @Override
    public Object semanticCheck(SymTab symTab) throws Exception {
        super.semanticCheck(symTab);
        if (TypeUtil.checkType(left, right)) {
            setType(AndExprOperator.castType(left.getType(), right.getType(), andExprOperator));
        }
        return null;
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
