package com.zck.plsql.syntax.expression.variableExpression;

import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.intermediate.SymTab;
import com.zck.plsql.syntax.ITreeNode;
import com.zck.plsql.syntax.expression.Expression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;

import java.util.Map;

public class VariableExpression extends Expression {

    private String name;

    @Override
    public Object semanticCheck(SymTab symTab) throws Exception {
        if (!symTab.containsVar(name)) {
            throw new Exception("variable not exist");
        }
        Expression expression = symTab.lookup(name);
        if (expression == null) {
            throw new Exception("variable not exist");
        }
        if (expression instanceof VariableExpression) {
            setType((VariableExpression) expression);
        } else {
            throw new Exception("variable not exist");
        }
        return null;
    }

    @Override
    public Object execute(InterpreterContext ctx) throws Exception {
        return ctx.findValue(name);
    }

    // 变量和常量的映射
    public void setValue(Map<String, ITreeNode> map, Expression expression) {
        map.put(name, expression);
    }

    public String getName() {
        return name;
    }

    public void setName(Map<String, Expression> map, String name) {
        this.name = name;
        map.put(name, null);
    }

    public void setName(String name) {
        this.name = name;
    }

}
