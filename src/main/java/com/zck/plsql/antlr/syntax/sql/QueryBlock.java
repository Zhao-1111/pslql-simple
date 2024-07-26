package com.zck.plsql.antlr.syntax.sql;

import com.zck.plsql.antlr.executor.compiler.CompilerContext;
import com.zck.plsql.antlr.intermediate.SymTab;
import com.zck.plsql.antlr.syntax.expression.variableExpression.VariableExpression;
import com.zck.plsql.antlr.syntax.statement.Statement;

import java.util.List;

public class QueryBlock extends Statement {
    private List<VariableExpression> intoList;
    private List<VariableExpression> ids;
    private List<String> tables;
    private String orignSQL;
    private int selectElements = 0;

    @Override
    public Object semanticCheck(SymTab symTab) throws Exception {
        if (intoList.size() != selectElements) {
            throw new Exception("intoList exception");
        }
        return null;
    }

    @Override
    public Object compileCodeGen(CompilerContext ctx) throws Exception {
        return null;
    }

    @Override
    public void setAttribute(Object key, Object value) {

    }

    @Override
    public Object getAttribute(Object key) {
        return null;
    }

    public List<VariableExpression> getIntoList() {
        return intoList;
    }

    public void setIntoList(List<VariableExpression> intoList) {
        this.intoList = intoList;
    }

    public String getOrignSQL() {
        return orignSQL;
    }

    public void setOrignSQL(String orignSQL) {
        this.orignSQL = orignSQL;
    }

    public List<VariableExpression> getIds() {
        return ids;
    }

    public void setIds(List<VariableExpression> ids) {
        this.ids = ids;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public int getSelectElements() {
        return selectElements;
    }

    public void setSelectElements(int selectElements) {
        this.selectElements = selectElements;
    }
}
