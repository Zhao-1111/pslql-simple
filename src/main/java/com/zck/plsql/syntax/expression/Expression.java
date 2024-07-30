package com.zck.plsql.syntax.expression;

import com.zck.plsql.executor.compiler.CompilerContext;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.intermediate.type.TypeUtil;
import com.zck.plsql.syntax.ITreeNode;
import com.zck.plsql.intermediate.type.TypeSpec;

public class Expression extends ITreeNode {
    public TypeSpec type = new TypeSpec(Type.NULL);

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

    public void setType(Type type) {
        this.type = new TypeSpec(type);
    }

    public void setType(String type) {
        Type plType = TypeUtil.getType(type);
        this.type = new TypeSpec(plType);
    }
}
