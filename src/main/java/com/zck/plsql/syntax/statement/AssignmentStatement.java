package com.zck.plsql.syntax.statement;

import com.zck.plsql.executor.compiler.CompilerContext;
import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.intermediate.SymTab;
import com.zck.plsql.syntax.expression.Expression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.variableExpression.VariableExpression;
import com.zck.plsql.intermediate.type.TypeUtil;
import com.zck.plsql.util.Mylogger;

/**
 * a := b;
 * 仅执行b表达式，将起值赋予a
 */
public class AssignmentStatement extends Statement {
    private VariableExpression variableExpression;
    private Expression ValueExpression;

    @Override
    public Object semanticCheck(SymTab symTab) throws Exception {
        variableExpression.semanticCheck(symTab);
        ValueExpression.semanticCheck(symTab);
        if (!TypeUtil.checkType(variableExpression, ValueExpression)) {
            throw new Exception("type exception");
        }
        return null;
    }

    @Override
    public Object execute(InterpreterContext ctx) throws Exception {
        ConstantExpression right = (ConstantExpression) ValueExpression.execute(ctx);
        if (!TypeUtil.checkType(variableExpression, right)) {
            throw new Exception("type Exception");
        } else {
            ctx.enterVarValue(variableExpression, right);
            Mylogger.debug("assignment execute:" + variableExpression.getName() + "=" + right.getConstValue());
        }
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

    public VariableExpression getVariableExpression() {
        return variableExpression;
    }

    public void setVariableExpression(VariableExpression variableExpression) {
        this.variableExpression = variableExpression;
    }

    public Expression getValueExpression() {
        return ValueExpression;
    }

    public void setValueExpression(VariableExpression valueExpression) {
        ValueExpression = valueExpression;
    }
}
