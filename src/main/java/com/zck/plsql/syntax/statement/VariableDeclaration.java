package com.zck.plsql.syntax.statement;

import com.zck.plsql.executor.compiler.CompilerContext;
import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.intermediate.ConstantFactory;
import com.zck.plsql.intermediate.SymTab;
import com.zck.plsql.intermediate.operator.AssignOperator;
import com.zck.plsql.intermediate.operator.OperatorInterface;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.syntax.expression.Expression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.variableExpression.VariableExpression;

public class VariableDeclaration extends Statement {
    private VariableExpression varExpression;
    private Expression valueExpression;

    @Override
    public Object semanticCheck(SymTab symTab) throws Exception {
        if (symTab.topContainsVar(varExpression.getName())) {
            throw new Exception("redefine variable");
        }
        symTab.put(varExpression.getName(), varExpression);
        if (valueExpression == null) {
            valueExpression = ConstantFactory.createConstant(Type.NULLTYPE);
        }
        if (!OperatorInterface.checkType(varExpression.getType(), valueExpression.getType(), AssignOperator.ASSIGN)) {
            throw new Exception("type exception");
        }
        return null;
    }

    @Override
    public Object execute(InterpreterContext ctx) throws Exception {
        ConstantExpression result = null;
        if (valueExpression == null) {
            result = ConstantFactory.createConstant(Type.NULLTYPE);
        } else {
            result = (ConstantExpression) valueExpression.executeException(ctx);
        }
        ctx.enterVarValue(varExpression, result);
        return null;
    }

    @Override
    public Object compileCodeGen(CompilerContext ctx) throws Exception {
        return null;
    }

    @Override
    public void setAttribute(Object key, Object value) {

    }

    @Override
    public Object getAttribute(Object key) {
        return null;
    }

    public VariableExpression getVarExpression() {
        return varExpression;
    }

    public void setVarExpression(VariableExpression varExpression) {
        this.varExpression = varExpression;
    }

    public Expression getValueExpression() {
        return valueExpression;
    }

    public void setValueExpression(Expression valueExpression) {
        this.valueExpression = valueExpression;
    }
}
