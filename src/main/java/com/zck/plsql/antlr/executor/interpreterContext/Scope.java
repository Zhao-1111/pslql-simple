package com.zck.plsql.antlr.executor.interpreterContext;

import com.zck.plsql.antlr.syntax.expression.Expression;
import com.zck.plsql.antlr.syntax.expression.VariableExpression;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private Map<VariableExpression, Expression> items = new HashMap<>();

    public void put(VariableExpression var, Expression value) {
        items.put(var, value);
    }

    public Expression get(VariableExpression var) {
        return items.get(var);
    }

    public Boolean contains(VariableExpression var) {
        return items.containsKey(var);
    }

    public Map<VariableExpression, Expression> getItems() {
        return items;
    }

    public void setItems(
            Map<VariableExpression, Expression> items) {
        this.items = items;
    }
}
