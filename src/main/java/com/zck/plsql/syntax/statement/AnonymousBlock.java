package com.zck.plsql.syntax.statement;

import com.zck.plsql.executor.compiler.CompilerContext;
import com.zck.plsql.intermediate.SymTab;
import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.executor.interpreter.Scope;
import com.zck.plsql.syntax.ITreeNode;

import java.util.HashMap;
import java.util.List;

/**
 * SeqOfDeclareSpecs
 * SeqOfStatements
 * ExceptionHandler
 * 异常处理语句执行控制
 */
public class AnonymousBlock extends Statement {
    private SeqOfDeclareSpecs seqOfDeclareSpecs;
    private SeqOfStatements seqOfStatements;
    private List<ExceptionHandler> exceptionHandlers;

    @Override
    public Object semanticCheck(SymTab symTab) throws Exception {
        symTab.push(new HashMap<>());
        super.semanticCheck(symTab);
        symTab.pop();
        return null;
    }

    @Override
    public Object execute(InterpreterContext ctx) throws Exception {
        ctx.getScopes().push(new Scope());
        for (ITreeNode node : getChildrens()) {
            node.executeException(ctx);
        }
        ctx.getScopes().pop();
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

    public void setSeqOfDeclareSpecs(SeqOfDeclareSpecs seqOfDeclareSpecs) {
        this.seqOfDeclareSpecs = seqOfDeclareSpecs;
    }

    public void setSeqOfStatements(SeqOfStatements seqOfStatements) {
        this.seqOfStatements = seqOfStatements;
    }

    public void setExceptionHandlers(List<ExceptionHandler> exceptionHandlers) {
        this.exceptionHandlers = exceptionHandlers;
    }
}
