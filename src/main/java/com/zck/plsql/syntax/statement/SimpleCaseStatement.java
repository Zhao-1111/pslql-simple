package com.zck.plsql.syntax.statement;

import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.intermediate.SymTab;
import com.zck.plsql.syntax.expression.Expression;

import java.util.List;

public class SimpleCaseStatement extends Statement{
    Expression caseExpr = null;
    List<Expression> whenList = null;
    List<Statement> thenStmt = null;
    Statement elseStmt = null;

    @Override
    public Object semanticCheck(SymTab symTab) throws Exception {
        return super.semanticCheck(symTab);
    }

    @Override
    public Object execute(InterpreterContext ctx) throws Exception {
        return super.executeException(ctx);
    }
}
