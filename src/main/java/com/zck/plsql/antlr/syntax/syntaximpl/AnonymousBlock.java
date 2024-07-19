package com.zck.plsql.antlr.syntax.syntaximpl;

import com.zck.plsql.antlr.executor.compiler.CompilerContext;
import com.zck.plsql.antlr.syntax.ITreeNode;
import com.zck.plsql.antlr.executor.interpreterContext.InterpreterContext;
import com.zck.plsql.antlr.executor.interpreterContext.Scope;

import java.util.List;

/**
 * SeqOfDeclareSpecs
 * SeqOfStatements
 * ExceptionHandler
 */
public class AnonymousBlock extends ITreeNode {
    private SeqOfDeclareSpecs seqOfDeclareSpecs;
    private SeqOfStatements seqOfStatements;
    private List<ExceptionHandler> exceptionHandlers;

    public Object execute(InterpreterContext ctx) throws Exception {
        ctx.getScopes().push(new Scope());
        for (ITreeNode node : getChildrens()) {
            node.execute(ctx);
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
