package com.zck.plsql.syntax.expression;

import com.zck.plsql.executor.compiler.CompilerContext;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeUtil;
import com.zck.plsql.syntax.ITreeNode;
import com.zck.plsql.syntax.expression.variableExpression.VariableExpression;

public class Expression extends ITreeNode {
    protected Type type = Type.NULLTYPE;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = TypeUtil.getType(type);
    }

    public void setType(VariableExpression variableExpression){
        this.type = variableExpression.getType();
    }

}
