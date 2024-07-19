package com.zck.plsql.antlr.executor.interpreterContext;

import com.zck.plsql.antlr.syntax.expression.Expression;
import com.zck.plsql.antlr.syntax.expression.VariableExpression;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InterpreterContext {
    private Stack<Scope> scopes = new Stack<>();

    private Map<String, String> values = new HashMap<>();

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
}
