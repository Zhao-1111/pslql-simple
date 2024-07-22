package com.zck.plsql.antlr.executor.interpreterContext;

import com.zck.plsql.antlr.intermediate.CallStack;
import com.zck.plsql.antlr.syntax.expression.Expression;
import com.zck.plsql.antlr.syntax.expression.variableExpression.VariableExpression;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InterpreterContext {
    // 变量栈
    private Stack<Scope> scopes = new Stack<>();
    // 语句栈
    private CallStack callStack = new CallStack();

    public Stack<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(Stack<Scope> scopes) {
        this.scopes = scopes;
    }

    public void put(VariableExpression var, Expression value) {
        scopes.peek().put(var, value);
        // values.put(var.getName(), value.get)
    }

    public Expression get(VariableExpression var) {
        Expression res = null;
        for (Scope scope : scopes) {
            if (scope.contains(var)) {
                res = scope.get(var);
            }
        }
        return res;
    }

    public CallStack getCallStack() {
        return callStack;
    }
}
