package com.zck.plsql.antlr.intermediate;

import com.zck.plsql.antlr.syntax.statement.Statement;

import java.util.Stack;

public class CallStack {
    private Stack<RuntimeStatementPointer> callStack = new Stack<>();

    public RuntimeStatementPointer peek() {
        if (this.callStack.isEmpty()) {
            return null;
        }
        return this.callStack.peek();
    }

    /**
     * 获取栈顶语句
     *
     * @return
     */
    public Statement getTopStatement() {
        RuntimeStatementPointer ptr = peek();
        if (ptr != null) {
            return ptr.getStatement();
        }
        return null;
    }

    /**
     * 获取栈底语句
     *
     * @return
     */
    public Statement getBottomStatement() {
        if (!this.callStack.isEmpty()) {
            return this.callStack.firstElement().getStatement();
        }
        return null;
    }

    /**
     * 语句入栈
     *
     * @param ptr
     */
    public void push(RuntimeStatementPointer ptr) {
        this.callStack.push(ptr);
    }

    public RuntimeStatementPointer pop() {
        if (!this.callStack.isEmpty()) {
            return this.callStack.pop();
        }
        return null;
    }
}
