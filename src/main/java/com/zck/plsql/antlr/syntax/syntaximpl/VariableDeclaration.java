package com.zck.plsql.antlr.syntax.syntaximpl;

import com.zck.plsql.antlr.executor.compiler.CompilerContext;
import com.zck.plsql.antlr.syntax.ITreeNode;
import com.zck.plsql.antlr.syntax.expression.Expression;
import com.zck.plsql.antlr.syntax.expression.variableExpression.VariableExpression;
import com.zck.plsql.antlr.intermediate.type.TypeUtil;

public class VariableDeclaration extends ITreeNode {
    private VariableExpression varExpression;
    private Expression valueExpression;

    @Override
    public Object semanticCheck() throws Exception {
        if (!TypeUtil.checkType(varExpression, valueExpression)) {
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

    public Expression getVarExpression() {
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
