package com.zck.plsql.antlr.syntax.expression;

import com.zck.plsql.antlr.executor.compiler.CompilerContext;
import com.zck.plsql.antlr.syntax.ITreeNode;
import com.zck.plsql.antlr.intermediate.type.TypeSpec;

public class Expression extends ITreeNode {
    public TypeSpec type;
    private String Value;

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

    public TypeSpec getType() {
        return type;
    }

    public void setType(String type) {
        this.type = new TypeSpec(type);
    }
}
