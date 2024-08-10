package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.intermediate.SymTab;
import com.zck.plsql.intermediate.operator.OperatorInterface;
import com.zck.plsql.syntax.expression.Expression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;

/**
 * 二元表达式通用逻辑
 * 根据不同对象的ClassName，去加载构建不同的operator
 *
 * @param <E>
 */
public abstract class LogicalBinaryExpression<E extends Enum<E> & OperatorInterface> extends Expression {
    protected Expression left, right;
    protected E operator;

    @Override
    public Object semanticCheck(SymTab symTab) throws Exception {
        super.semanticCheck(symTab);
        setType(OperatorInterface.castType(left.getType(), right.getType(), operator));
        return null;
    }

    @Override
    public ConstantExpression execute(InterpreterContext ctx) throws Exception {
        ConstantExpression leftConstantValue = (ConstantExpression) left.executeException(ctx);
        ConstantExpression rightConstantValue = (ConstantExpression) right.executeException(ctx);
        return this.operator.apply(leftConstantValue, rightConstantValue);
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

    public E getOperator() {
        return operator;
    }

    public void setOperator(E operator) {
        this.operator = operator;
    }
}
