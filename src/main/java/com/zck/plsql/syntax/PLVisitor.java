package com.zck.plsql.syntax;

import com.zck.plsql.antlr.PlSqlParser;
import com.zck.plsql.antlr.PlSqlParserBaseVisitor;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.variableExpression.VariableExpression;
import com.zck.plsql.syntax.sql.QueryBlock;
import com.zck.plsql.syntax.statement.AnonymousBlock;
import com.zck.plsql.syntax.statement.AssignmentStatement;
import com.zck.plsql.syntax.statement.DeclareSpec;
import com.zck.plsql.syntax.statement.SeqOfDeclareSpecs;
import com.zck.plsql.syntax.statement.SeqOfStatements;
import com.zck.plsql.syntax.statement.VariableDeclaration;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PLVisitor extends PlSqlParserBaseVisitor {
    private String orignStmt;
    // 临时sql变量
    private List<VariableExpression> intoList;
    private List<String> tables;
    private List<VariableExpression> ids;
    private boolean enterSQL = false;

    public PLVisitor(String orignStmt) {
        this.orignStmt = orignStmt;
    }

    public Object visitChildren(RuleNode node, ITreeNode tree) {
        if (node == null) {
            return null;
        }
        Object result = this.defaultResult();
        int n = node.getChildCount();

        for (int i = 0; i < n && this.shouldVisitNextChild(node, result); ++i) {
            ParseTree c = node.getChild(i);
            ITreeNode childResult = (ITreeNode) c.accept(this);
            if (childResult != null) {
                tree.getChildrens().add(childResult);
            }
            result = this.aggregateResult(result, childResult);
        }
        return result;
    }

    @Override
    public Object visitAnonymous_block(PlSqlParser.Anonymous_blockContext ctx) {
        AnonymousBlock anonymousBlock = new AnonymousBlock();
        visitChildren(ctx, anonymousBlock);
        return anonymousBlock;
    }

    @Override
    public Object visitSeq_of_declare_specs(PlSqlParser.Seq_of_declare_specsContext ctx) {
        SeqOfDeclareSpecs seqOfDeclareSpecs = new SeqOfDeclareSpecs();
        visitChildren(ctx, seqOfDeclareSpecs);
        return seqOfDeclareSpecs;
    }


    @Override
    public Object visitDeclare_spec(PlSqlParser.Declare_specContext ctx) {
        DeclareSpec declareSpec = new DeclareSpec();
        visitChildren(ctx, declareSpec);
        return declareSpec;
    }

    @Override
    public Object visitVariable_declaration(PlSqlParser.Variable_declarationContext ctx) {
        VariableDeclaration variableDeclaration = new VariableDeclaration();
        VariableExpression varExpression = new VariableExpression();
        varExpression.setName(ctx.identifier().getText());
        varExpression.setType(ctx.type_spec().getText());
        variableDeclaration.setVarExpression(varExpression);
        if (ctx.default_value_part() != null) {
            ConstantExpression valueExpression = new ConstantExpression();
            valueExpression.setConstValue(visitChildren(ctx.default_value_part().expression()));
            variableDeclaration.setValueExpression(valueExpression);
        }
        return variableDeclaration;
    }

    @Override
    public Object visitSeq_of_statements(PlSqlParser.Seq_of_statementsContext ctx) {
        SeqOfStatements seqOfStatements = new SeqOfStatements();
        visitChildren(ctx, seqOfStatements);
        return seqOfStatements;
    }

    @Override
    public Object visitAssignment_statement(PlSqlParser.Assignment_statementContext ctx) {
        AssignmentStatement assignmentStatement = new AssignmentStatement();

        VariableExpression variableExpression = new VariableExpression();
        variableExpression.setName(ctx.general_element().getText());
        assignmentStatement.setVariableExpression(variableExpression);

        VariableExpression valueExpression = new VariableExpression();
        valueExpression.setName(ctx.expression().getText());
        assignmentStatement.setValueExpression(valueExpression);

        return assignmentStatement;
    }

    @Override
    public Object visitQuery_block(PlSqlParser.Query_blockContext ctx) {
        try {
            QueryBlock queryBlock = new QueryBlock();
            intoList = new ArrayList<>();
            tables = new ArrayList<>();
            ids = new ArrayList<>();
            int selectElements = 0;
            for (ParseTree tree : ctx.into_clause().children) {
                Object node = tree.accept(this);
                if (node instanceof VariableExpression) {
                    intoList.add((VariableExpression) node);
                }
            }
            this.enterSQL = true;
            for (ParserRuleContext node : ctx.selected_list().select_list_elements()) {
                if (node instanceof PlSqlParser.Select_list_elementsContext) {
                    selectElements++;
                    visitChildren(node);
                }

            }
            visitChildren(ctx.selected_list(), queryBlock);
            visitChildren(ctx.from_clause(), queryBlock);
            visitChildren(ctx.where_clause(), queryBlock);
            visitChildren(ctx.order_by_clause(), queryBlock);
            visitChildren(ctx.model_clause(), queryBlock);
            visitChildren(ctx.offset_clause(), queryBlock);
            for (ParserRuleContext node : ctx.group_by_clause()) {
                if (node instanceof PlSqlParser.Group_by_clauseContext) {
                    visitChildren(node, queryBlock);
                }
            }
            queryBlock.setIntoList(intoList);
            queryBlock.setTables(tables);
            queryBlock.setIds(ids);
            queryBlock.setSelectElements(selectElements);
            int startIndex = ctx.getStart().getStartIndex();
            int stopIndex = ctx.getStop().getStopIndex();
            int intoStartIndex = ctx.into_clause().getStart().getStartIndex();
            int intoStopIndex = ctx.into_clause().getStop().getStopIndex();
            queryBlock.setOrignSQL(
                    this.orignStmt.substring(startIndex, intoStartIndex) + this.orignStmt.substring(intoStopIndex + 1,
                            stopIndex + 1));
            return queryBlock;
        } finally {
            this.enterSQL = false;
        }

    }

    @Override
    public Object visitGeneral_element(PlSqlParser.General_elementContext ctx) {
        VariableExpression variableExpression = new VariableExpression();
        variableExpression.setName(ctx.getText());
        if (enterSQL) {
            ids.add(variableExpression);
            return null;
        }
        return variableExpression;
    }

    @Override
    public Object visitTableview_name(PlSqlParser.Tableview_nameContext ctx) {
        tables.add(ctx.getText());
        return null;
    }

    // 声明常量节点
    @Override
    public Object visitConstant(PlSqlParser.ConstantContext ctx) {
        if (ctx.numeric() != null) {
            return new BigDecimal(ctx.getText());
        }
        if (ctx.quoted_string() != null) {
            return ctx.getText();
        }
        return ctx.getText();
    }

}
