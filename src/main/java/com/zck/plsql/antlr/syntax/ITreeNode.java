package com.zck.plsql.antlr.syntax;

import com.zck.plsql.antlr.executor.compiler.CompilerContext;
import com.zck.plsql.antlr.executor.interpreterContext.InterpreterContext;

import java.util.ArrayList;
import java.util.List;

public abstract class ITreeNode {
    /**
     * 用以存放子节点
     */
    public List<ITreeNode> childrens = new ArrayList<>();

    /**
     * 语义检查
     * @return
     */
    public Object semanticCheck() throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.semanticCheck();
        }
        return null;
    };

    /**
     * 解释执行
     * @param ctx
     * @return
     * @throws Exception
     */
    public Object stepExecute(InterpreterContext ctx)throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.stepExecute(ctx);
        }
        return null;
    }

    /**
     * 编译执行前IR代码生成
     * @param ctx
     * @return
     * @throws Exception
     */
    public abstract Object compileCodeGen(CompilerContext ctx)throws Exception;

    /**
     * set value
     * @param key
     * @param value
     */
    public abstract void setAttribute(Object key, Object value);

    /**
     * get value by key
     *
     * @param key
     * @return
     */
    public abstract Object getAttribute(Object key);

    public List<ITreeNode> getChildrens() {
        return childrens;
    }

}
