package com.zck.plsql.antlr.syntax;

import com.zck.plsql.antlr.executor.compiler.CompilerContext;
import com.zck.plsql.antlr.executor.interpreter.InterpreterContext;
import com.zck.plsql.antlr.intermediate.SymTab;

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
    public Object semanticCheck(SymTab symTab) throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.semanticCheck(symTab);
        }
        return null;
    };

    /**
     * 单步解释执行
     * @param ctx
     * @return
     * @throws Exception
     */
    public Object stepExec(InterpreterContext ctx)throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.stepExec(ctx);
        }
        return null;
    }

    /**
     * 解释执行
     * @param ctx
     * @return
     * @throws Exception
     */
    public Object execute(InterpreterContext ctx)throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.execute(ctx);
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
