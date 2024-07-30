package com.zck.plsql.intermediate;

import com.zck.plsql.syntax.expression.Expression;

import java.util.HashMap;
import java.util.Stack;

public class SymTab extends Stack<HashMap<String, Expression>> {
    public boolean topContainsVar(String name) {
        if (!isEmpty()) {
            return peek().containsKey(name);
        }
        return false;
    }

    public boolean containsVar(String name) {
        if (!isEmpty()) {
            for (int i = size() - 1; i >= 0; i--) {
                if (get(i).containsKey(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Expression lookup(String name) {
        for (int i = size() - 1; i >= 0; i--) {
            if (get(i).containsKey(name)) {
                return get(i).get(name);
            }
        }
        return null;
    }

    public void put(String name, Expression expression) throws Exception {
        if (isEmpty()) {
            throw new Exception("symtab is empty");
        }
        peek().put(name, expression);
    }
}
