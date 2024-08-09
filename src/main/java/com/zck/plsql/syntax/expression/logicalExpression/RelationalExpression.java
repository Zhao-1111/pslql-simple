package com.zck.plsql.syntax.expression.logicalExpression;

/**
 * rule1
 * relational_expression
 * : relational_expression relational_operator relational_expression
 * | compound_expression
 * ;
 * <p>
 * rule2
 * relational_operator
 * : '='
 * | (NOT_EQUAL_OP | '<' '>' | '!' '=' | '^' '=')
 * | ('<' | '>') '='?
 * ;
 */
public class RelationalExpression extends LogicalBinaryExpression {

}
