package com.zck.plsql.syntax.expression.logicalExpression;

import com.zck.plsql.intermediate.operator.ConcatenationOperator;

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
 */
public class Concatenation extends LogicalBinaryExpression<ConcatenationOperator> {

}

