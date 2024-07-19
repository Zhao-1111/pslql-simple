package com.zck.plsql.antlr.syntax.expression;

import com.zck.plsql.antlr.syntax.ITreeNode;

import java.util.Map;

public class VariableExpression extends Expression {

    private String name;

    public void setVar(Map<String, ITreeNode> map, Expression expression) {
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
