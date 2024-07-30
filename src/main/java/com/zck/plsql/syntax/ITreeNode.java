package com.zck.plsql.syntax;

import com.zck.plsql.executor.compiler.CompilerContext;
import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.intermediate.SymTab;

import java.util.ArrayList;
import java.util.List;

public abstract class ITreeNode {
    /**
     * 用以存放子节点
     */
    public List<ITreeNode> childrens = new ArrayList<>();

    /**
     * 语义检查
     *
     * @return
     */
    public Object semanticCheck(SymTab symTab) throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.semanticCheck(symTab);
        }
        return null;
    }

    ;

    /**
     * 单步解释执行
     *
     * @param ctx
     * @return
     * @throws Exception
     */
    public Object stepExec(InterpreterContext ctx) throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.stepExec(ctx);
        }
        return null;
    }

    /**
     * 解释执行
     *
     * @param ctx
     * @return
     * @throws Exception
     */
    public Object executeException(InterpreterContext ctx) throws Exception {
        Object obj = null;
        try {
            obj = execute(ctx);
        } catch (Exception e) {
            exceptionHandle(ctx, e);

        }
        return obj;
    }

    public Object execute(InterpreterContext ctx) throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.executeException(ctx);
        }
        return null;
    }

    public void exceptionHandle(InterpreterContext ctx, Exception e) throws Exception {
        throw e;
    }

    /**
     * 编译执行前IR代码生成
     *
     * @param ctx
     * @return
     * @throws Exception
     */
    public abstract Object compileCodeGen(CompilerContext ctx) throws Exception;

    /**
     * set value
     *
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
