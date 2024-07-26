package com.zck.plsql.antlr.syntax.statement;

import com.zck.plsql.antlr.executor.compiler.CompilerContext;
import com.zck.plsql.antlr.executor.interpreter.InterpreterContext;
import com.zck.plsql.antlr.executor.interpreter.CallStack;
import com.zck.plsql.antlr.executor.interpreter.RuntimeStatementPointer;
import com.zck.plsql.antlr.executor.interpreter.Scope;
import com.zck.plsql.antlr.syntax.ITreeNode;

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

    public void toTreeString(StringBuffer sb, String padding, String pointer) {
        sb.append(padding);
        sb.append(pointer);
        sb.append(getClass().getSimpleName());
        sb.append("\n");

        String paddingForChildren = padding + "│  ";
        String pointerForLastChild = "└──";
        String pointerForChild = "├──";

        for (int i = 0; i < childrens.size(); i++) {
            // 当前只显示statement
            if (childrens.get(i) instanceof Statement) {
                Statement child = (Statement) childrens.get(i);
                if (i == childrens.size() - 1) {
                    child.toTreeString(sb, paddingForChildren, pointerForLastChild);
                } else {
                    child.toTreeString(sb, paddingForChildren, pointerForChild);
                }
            }
        }
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
