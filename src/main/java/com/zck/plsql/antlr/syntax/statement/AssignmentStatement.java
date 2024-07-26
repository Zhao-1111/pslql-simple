package com.zck.plsql.antlr.syntax.statement;

import com.zck.plsql.antlr.executor.compiler.CompilerContext;
import com.zck.plsql.antlr.intermediate.SymTab;
import com.zck.plsql.antlr.syntax.expression.Expression;
import com.zck.plsql.antlr.syntax.expression.variableExpression.VariableExpression;
import com.zck.plsql.antlr.intermediate.type.TypeUtil;

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
