package com.zck.plsql.syntax.statement;

import com.zck.plsql.executor.compiler.CompilerContext;
import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.executor.interpreter.CallStack;
import com.zck.plsql.executor.interpreter.RuntimeStatementPointer;
import com.zck.plsql.executor.interpreter.Scope;
import com.zck.plsql.syntax.ITreeNode;

public class Statement extends ITreeNode {
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

    /**
     * 语句进入是入栈，若栈顶已经为当前语句，则返回false
     *
     * @param ctx
     * @return
     */
    public boolean onEnteringStatement(InterpreterContext ctx) {
        CallStack callStack = ctx.getCallStack();
        RuntimeStatementPointer ptr = callStack.peek();
        if (ptr == null || ptr.getStatement() != this) {
            RuntimeStatementPointer newPtr = new RuntimeStatementPointer(this);
            callStack.push(newPtr);
            return true;
        }
        return false;
    }

    /**
     * 语句退栈
     *
     * @param ctx
     * @return
     */
    public boolean onExitingStatement(InterpreterContext ctx) throws Exception {
        CallStack callStack = ctx.getCallStack();
        RuntimeStatementPointer ptr = callStack.peek();
        Statement statement = ptr.getStatement();
        if (statement == this) {
            callStack.pop();
            Statement top = callStack.getTopStatement();
            if (top == null) {
                return true;
            }
            boolean canNext = top.nextSubStatement(ctx);
            if (!canNext) {
                top.onExitingStatement(ctx);
            }
            return true;
        }
        return false;
    }

    public boolean nextSubStatement(InterpreterContext ctx) throws Exception {
        CallStack callStack = new CallStack();
        RuntimeStatementPointer ptr = callStack.peek();
        Statement statement = ptr.getStatement();
        if (statement == this) {
            // 这里逻辑是错的
            for (ITreeNode child : childrens) {
                child.stepExec(ctx);
            }
        }
        return false;
    }

    @Override
    public Object stepExec(InterpreterContext ctx) throws Exception {
        if (!onEnteringStatement(ctx)) {
            ctx.getScopes().push(new Scope());
            for (ITreeNode node : getChildrens()) {
                node.stepExec(ctx);
            }
            onExitingStatement(ctx);
        }
        return null;
    }
}
