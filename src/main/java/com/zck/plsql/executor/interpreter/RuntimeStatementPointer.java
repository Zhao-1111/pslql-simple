package com.zck.plsql.executor.interpreter;

import com.zck.plsql.syntax.statement.Statement;

public class RuntimeStatementPointer {
    private Statement statement;

    public RuntimeStatementPointer(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
