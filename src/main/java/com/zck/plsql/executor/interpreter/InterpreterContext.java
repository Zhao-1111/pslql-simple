package com.zck.plsql.executor.interpreter;

import com.zck.plsql.intermediate.ConstantFactory;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.syntax.expression.Expression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.variableExpression.VariableExpression;

import java.util.Stack;

public class InterpreterContext {
    // 变量栈
    private Stack<Scope> scopes = new Stack<>();
    // 语句栈 for 单步执行
    private CallStack callStack = new CallStack();

    public Stack<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(Stack<Scope> scopes) {
        this.scopes = scopes;
    }

    public void put(String var, ConstantExpression value) {
        scopes.peek().put(var, value);
    }

    public ConstantExpression findValue(String name){
        if (!scopes.isEmpty()) {
            for (int i = scopes.size() - 1; i >= 0; i--) {
                if (scopes.get(i).containsKey(name)) {
                    return scopes.get(i).get(name);
                }
            }
        }
        return ConstantFactory.createConstant(Type.NULLTYPE);
    }
    public Expression get(VariableExpression var) {
        Expression res = null;
        for (Scope scope : scopes) {
            if (scope.containsKey(var.getName())) {
                res = scope.get(var.getName());
            }
        }
        return res;
    }

    public CallStack getCallStack() {
        return callStack;
    }

    public boolean enterVarValue(VariableExpression left, ConstantExpression right) throws Exception {
        return getScopes().peek().setItem(left, right);
    }
}
