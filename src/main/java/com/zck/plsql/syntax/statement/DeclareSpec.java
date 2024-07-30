package com.zck.plsql.syntax.statement;

import com.zck.plsql.executor.compiler.CompilerContext;

public class DeclareSpec extends Statement {
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
}
