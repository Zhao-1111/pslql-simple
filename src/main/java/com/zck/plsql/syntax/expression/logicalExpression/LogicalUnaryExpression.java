package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.intermediate.ConstantFactory;
import com.zck.plsql.intermediate.SymTab;
import com.zck.plsql.intermediate.operator.OperatorInterface;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.syntax.expression.Expression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;

public abstract class LogicalUnaryExpression<E extends Enum<E> & OperatorInterface> extends Expression {
    protected Expression expr;
    protected E operator;

    @Override
    public Object semanticCheck(SymTab symTab) throws Exception {
        super.semanticCheck(symTab);
        // 若无操作符号，则去子表达式类型
        if (operator == null) {
            setType(expr.getType());
            return null;
        }
        setType(OperatorInterface.castType(expr.getType(), Type.NULLTYPE, operator));
        return null;
    }

    @Override
    public ConstantExpression execute(InterpreterContext ctx) throws Exception {
        ConstantExpression constantValue = (ConstantExpression) expr.executeException(ctx);
        if (operator == null) {
            return constantValue;
        }
        return this.operator.apply(constantValue, ConstantFactory.createConstant(Type.NULLTYPE), type);
    }


    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public E getOperator() {
        return operator;
    }

    public void setOperator(E operator) {
        this.operator = operator;
    }
}
