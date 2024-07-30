package com.zck.plsql.executor.interpreter;

import com.zck.plsql.syntax.expression.Expression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.variableExpression.VariableExpression;
import com.zck.plsql.util.Mylogger;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private Map<String, ConstantExpression> items = new HashMap<>();

    public void put(String var, ConstantExpression value) {
        items.put(var, value);
    }

    public ConstantExpression get(String var) {
        return items.get(var);
    }

    public Boolean containsKey(String var) {
        return items.containsKey(var);
    }

    public Map<String, ConstantExpression> getItems() {
        return items;
    }

    public boolean setItem(VariableExpression left, ConstantExpression right) throws Exception {
        if (left == null || right == null) {
            Mylogger.error("declare variable exception");
            throw new Exception("declare variable exception");
        }
        // todo:类型赋值
        items.put(left.getName(),right);
        Mylogger.debug("add variable:" + left.getName() + "="+ right.getConstValue());
        return false;
    }
}
