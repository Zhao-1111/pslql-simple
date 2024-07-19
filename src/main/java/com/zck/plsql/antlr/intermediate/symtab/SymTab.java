package com.zck.plsql.antlr.intermediate.symtab;

import com.zck.plsql.antlr.syntax.expression.Expression;

import java.util.Map;
import java.util.Stack;

public class SymTab {
    private final Stack<Map<String, Expression>> stack = new Stack<>();

    public Stack<Map<String, Expression>> getStack() {
        return stack;
    }
}
