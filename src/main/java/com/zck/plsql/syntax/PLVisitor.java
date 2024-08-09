package com.zck.plsql.syntax;

import com.zck.plsql.antlr.PlSqlParser;
import com.zck.plsql.antlr.PlSqlParser.AtomContext;
import com.zck.plsql.antlr.PlSqlParser.Compound_expressionContext;
import com.zck.plsql.antlr.PlSqlParser.ConcatenationContext;
import com.zck.plsql.antlr.PlSqlParser.Logical_expressionContext;
import com.zck.plsql.antlr.PlSqlParser.Model_expressionContext;
import com.zck.plsql.antlr.PlSqlParser.Multiset_expressionContext;
import com.zck.plsql.antlr.PlSqlParser.Relational_expressionContext;
import com.zck.plsql.antlr.PlSqlParser.Simple_case_statementContext;
import com.zck.plsql.antlr.PlSqlParser.Unary_expressionContext;
import com.zck.plsql.antlr.PlSqlParser.Unary_logical_expressionContext;
import com.zck.plsql.antlr.PlSqlParserBaseVisitor;
import com.zck.plsql.intermediate.operator.ConcatenationOperator;
import com.zck.plsql.intermediate.operator.RelationalExprOperator;
import com.zck.plsql.intermediate.operator.UnaryExprOperator;
import com.zck.plsql.intermediate.type.Type;
import com.zck.plsql.syntax.expression.Expression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression.BooleanValue;
import com.zck.plsql.syntax.expression.logicalExpression.AndExpression;
import com.zck.plsql.syntax.expression.logicalExpression.Concatenation;
import com.zck.plsql.syntax.expression.logicalExpression.ModelExpression;
import com.zck.plsql.syntax.expression.logicalExpression.MultisetExpression;
import com.zck.plsql.syntax.expression.logicalExpression.OrExpression;
import com.zck.plsql.syntax.expression.logicalExpression.RelationalExpression;
import com.zck.plsql.syntax.expression.logicalExpression.UnaryExpression;
import com.zck.plsql.syntax.expression.logicalExpression.UnaryLogicalExpression;
import com.zck.plsql.syntax.expression.variableExpression.VariableExpression;
import com.zck.plsql.syntax.sql.QueryBlock;
import com.zck.plsql.syntax.statement.AnonymousBlock;
import com.zck.plsql.syntax.statement.AssignmentStatement;
import com.zck.plsql.syntax.statement.DeclareSpec;
import com.zck.plsql.syntax.statement.SeqOfDeclareSpecs;
import com.zck.plsql.syntax.statement.SeqOfStatements;
import com.zck.plsql.syntax.statement.SimpleCaseStatement;
import com.zck.plsql.syntax.statement.Statement;
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
    private Exception exception = null;

    public Exception getException() {
        return exception;
    }

    public PLVisitor(String orignStmt) {
        this.orignStmt = orignStmt;
    }

    //
    public Object visit(ParseTree tree, ITreeNode parent) {
        Object node = tree.accept(this);
        if (node instanceof Statement || node instanceof Expression) {
            parent.getChildrens().add((ITreeNode) node);
        }
        if (node == null) {
            this.exception = new Exception(tree.getText()+":Not support");
        }
        return node;
    }

    public Object visitChildren(RuleNode node, ITreeNode parent) {
        if (node == null) {
            return null;
        }
        Object result = this.defaultResult();
        int n = node.getChildCount();

        for (int i = 0; i < n && this.shouldVisitNextChild(node, result); ++i) {
            ParseTree c = node.getChild(i);
            ITreeNode childResult = (ITreeNode) c.accept(this);
            if (childResult != null) {
                // 将子节点按顺序置于节点上，用于后续遍历
                parent.getChildrens().add(childResult);
            } else {
                this.exception = new Exception(c.getText() + ":Not support");
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
            variableDeclaration.setValueExpression(
                    (Expression) visit(ctx.default_value_part().expression(), variableDeclaration));
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
        variableExpression.setName(((VariableExpression) visit(ctx.general_element())).getName());
        assignmentStatement.setVariableExpression(variableExpression);
        assignmentStatement.getChildrens().add(variableExpression);

        assignmentStatement.setValueExpression((Expression) visit(ctx.expression(), assignmentStatement));

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
    public Object visitTableview_name(PlSqlParser.Tableview_nameContext ctx) {
        tables.add(ctx.getText());
        return null;
    }

    /**
     * else语句不是必须
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitSimple_case_statement(Simple_case_statementContext ctx) {
        SimpleCaseStatement simpleCaseStatement = new SimpleCaseStatement();
        Expression caseExpr = new Expression();
        // todo case
        return simpleCaseStatement;
    }

    /**
     * 逻辑表达式,
     * logical_expression
     * : unary_logical_expression
     * | logical_expression AND logical_expression
     * | logical_expression OR logical_expression
     * ;
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitLogical_expression(Logical_expressionContext ctx) {
        // 当前为orExpr
        if (ctx.OR() != null) {
            OrExpression orExpression = new OrExpression();
            orExpression.setLeft((Expression) visit(ctx.logical_expression(0), orExpression));
            orExpression.setRight((Expression) visit(ctx.logical_expression(1), orExpression));
            return orExpression;
        }
        // 当前为andExpr
        if (ctx.AND() != null) {
            AndExpression andExpression = new AndExpression();
            andExpression.setLeft((Expression) visit(ctx.logical_expression(0), andExpression));
            andExpression.setRight((Expression) visit(ctx.logical_expression(1), andExpression));
            return andExpression;
        }
        // 当前为unaryLoagicalExpr
        if (ctx.unary_logical_expression() != null) {
            return visit(ctx.unary_logical_expression());
        }
        return null;
    }

    /**
     * unary_logical_expression
     * : NOT? multiset_expression unary_logical_operation?
     * ;
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitUnary_logical_expression(Unary_logical_expressionContext ctx) {
        UnaryLogicalExpression unaryLogicalExpression = new UnaryLogicalExpression();
        unaryLogicalExpression.setNot(ctx.NOT() != null);
        unaryLogicalExpression.setMultisetExpression(
                (Expression) visit(ctx.multiset_expression(), unaryLogicalExpression));
        return unaryLogicalExpression;
    }

    /**
     * multiset_expression
     * : relational_expression (multiset_type = (MEMBER | SUBMULTISET) OF? concatenation)?
     * | multiset_expression MULTISET multiset_operator = (EXCEPT | INTERSECT | UNION) (
     * ALL
     * | DISTINCT
     * )? relational_expression
     * ;
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitMultiset_expression(Multiset_expressionContext ctx) {
        MultisetExpression multisetExpression = new MultisetExpression();
        multisetExpression.setRelationalExpression((Expression) visit(ctx.relational_expression(), multisetExpression));
        return multisetExpression;
    }

    /**
     * relational_expression
     * : relational_expression relational_operator relational_expression
     * | compound_expression
     * ;
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitRelational_expression(Relational_expressionContext ctx) {
        if (ctx.relational_operator() != null) {
            RelationalExpression relationalExpression = new RelationalExpression();
            relationalExpression.setLeft((Expression) visit(ctx.relational_expression(0), relationalExpression));
            relationalExpression.setRight((Expression) visit(ctx.relational_expression(1), relationalExpression));
            String operator = ctx.relational_operator().getText();
            relationalExpression.setExprOperator(RelationalExprOperator.fromString(operator));
            return relationalExpression;
        } else {
            return visit(ctx.compound_expression());
        }
    }

    /**
     * compound_expression
     * : concatenation (
     * NOT? (
     * IN in_elements
     * | BETWEEN between_elements
     * | like_type = (LIKE | LIKEC | LIKE2 | LIKE4) concatenation (ESCAPE concatenation)?
     * )
     * )?
     * ;
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitCompound_expression(Compound_expressionContext ctx) {
        return visit(ctx.concatenation(0));
    }

    /**
     * concatenation
     * : model_expression (AT (LOCAL | TIME ZONE concatenation) | interval_expression)? (
     * ON OVERFLOW (TRUNCATE | ERROR)
     * )?
     * | concatenation op = DOUBLE_ASTERISK concatenation
     * | concatenation op = (ASTERISK | SOLIDUS | MOD) concatenation
     * | concatenation op = (PLUS_SIGN | MINUS_SIGN) concatenation
     * | concatenation BAR BAR concatenation
     * | concatenation COLLATE column_collation_name
     * ;
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitConcatenation(ConcatenationContext ctx) {
        if (ctx.model_expression() != null) {
            return visit(ctx.model_expression());
        } else {
            Concatenation concatenation = new Concatenation();
            concatenation.setLeft((Expression) visit(ctx.concatenation(0), concatenation));
            concatenation.setRight((Expression) visit(ctx.concatenation(1), concatenation));
            if (ctx.BAR(0) != null) {
                concatenation.setOperator(ConcatenationOperator.BARBAR);
                return concatenation;
            } else if (ctx.op != null) {
                concatenation.setOperator(ConcatenationOperator.fromString(ctx.op.getText()));
                return concatenation;
            }
            return null;
        }
    }

    /**
     * model_expression
     * : unary_expression ('[' model_expression_element ']')?
     * ;
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitModel_expression(Model_expressionContext ctx) {
        ModelExpression modelExpression = new ModelExpression();
        modelExpression.setUnaryExpr((Expression) visit(ctx.unary_expression(), modelExpression));
        return super.visitModel_expression(ctx);
    }

    /**
     * unary_expression
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitUnary_expression(Unary_expressionContext ctx) {
        if (ctx.atom() != null) {
            return visit(ctx.atom());
        } else {
            UnaryExpression unaryExpression = new UnaryExpression();
            unaryExpression.setExpression((Expression) visit(ctx.unary_expression(), unaryExpression));
            if (ctx.PLUS_SIGN() != null) {
                unaryExpression.setUnaryExprOperator(UnaryExprOperator.POSITIVE);
                return unaryExpression;
            } else if (ctx.MINUS_SIGN() != null) {
                unaryExpression.setUnaryExprOperator(UnaryExprOperator.NEGATIVE);
                return unaryExpression;
            }
            return null;
        }
    }

    /**
     * atom
     * : bind_variable
     * | constant
     * | inquiry_directive
     * | general_element outer_join_sign?
     * | '(' subquery ')' subquery_operation_part*
     * | '(' expressions ')'
     * ;
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitAtom(AtomContext ctx) {
        if (ctx.constant() != null) {
            return visit(ctx.constant());
        }
        if (ctx.general_element() != null) {
            return visit(ctx.general_element());
        }
        if (ctx.expressions() != null) {
            // todo 当前先认为仅有一个
            return visit(ctx.expressions().expression(0));
        }
        return null;
    }

    /**
     * constant
     * : TIMESTAMP (quoted_string | bind_variable) (AT TIME ZONE quoted_string)?
     * | INTERVAL (quoted_string | bind_variable | general_element_part) (
     * YEAR
     * | MONTH
     * | DAY
     * | HOUR
     * | MINUTE
     * | SECOND
     * ) ('(' (UNSIGNED_INTEGER | bind_variable) (',' (UNSIGNED_INTEGER | bind_variable))? ')')? (
     * TO (DAY | HOUR | MINUTE | SECOND ('(' (UNSIGNED_INTEGER | bind_variable) ')')?)
     * )?
     * | numeric
     * | DATE quoted_string
     * | quoted_string
     * | NULL_
     * | TRUE
     * | FALSE
     * | DBTIMEZONE
     * | SESSIONTIMEZONE
     * | MINVALUE
     * | MAXVALUE
     * | DEFAULT
     * ;
     *
     * @param ctx the parse tree
     * @return
     */
    @Override
    public Object visitConstant(PlSqlParser.ConstantContext ctx) {
        ConstantExpression constantExpression = new ConstantExpression();
        if (ctx.numeric() != null) {
            // 数值类型常量
            constantExpression.setConstValue(new BigDecimal(ctx.numeric().getText()));
            return constantExpression;
        }
        if (ctx.quoted_string() != null) {
            // 字符类型
            constantExpression.setConstValue(ctx.getText());
            return constantExpression;
        }
        if (ctx.NULL_() != null) {
            // NULL
            constantExpression.setType(Type.NULLTYPE);
            return constantExpression;
        }
        if (ctx.TRUE() != null) {
            constantExpression.setConstValue(BooleanValue.TRUE);
            return null;
        }
        return constantExpression;
    }

    @Override
    public Object visitGeneral_element(PlSqlParser.General_elementContext ctx) {
        VariableExpression variableExpression = new VariableExpression();
        // todo variable
        variableExpression.setName(ctx.getText());
        if (enterSQL) {
            ids.add(variableExpression);
            return null;
        }
        return variableExpression;
    }

}
