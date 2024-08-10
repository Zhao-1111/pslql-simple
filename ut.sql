-- Simple incomplete use cases
-- expr
/*
rule1
logical_expression
    : unary_logical_expression
    | logical_expression AND logical_expression
    | logical_expression OR logical_expression
    ;

rule2
unary_logical_expression
    : NOT? multiset_expression unary_logical_operation?
    ;

rule3
compound_expression
    : concatenation (
        NOT? (
            IN in_elements
            | BETWEEN between_elements
            | like_type = (LIKE | LIKEC | LIKE2 | LIKE4) concatenation (ESCAPE concatenation)?
        )
    )?
    ;

 */
declare
    a boolean := true and false;
    b boolean := true or false;
    c boolean := a and b or b;
    d int := 2;
begin
    a := b;
end;

/*
rule1
relational_expression
    : relational_expression relational_operator relational_expression
    | compound_expression
    ;
rule2
relational_operator
    : '='
    | (NOT_EQUAL_OP | '<' '>' | '!' '=' | '^' '=')
    | ('<' | '>') '='?
    ;
 */
declare
    a int := 1;
    b int := 2;
    c boolean := a > b;
begin
    a := b;
end;

/*
concatenation
    : model_expression (AT (LOCAL | TIME ZONE concatenation) | interval_expression)? (
        ON OVERFLOW (TRUNCATE | ERROR)
    )?
    | concatenation op = DOUBLE_ASTERISK concatenation
    | concatenation op = (ASTERISK | SOLIDUS | MOD) concatenation
    | concatenation op = (PLUS_SIGN | MINUS_SIGN) concatenation
    | concatenation BAR BAR concatenation
    | concatenation COLLATE column_collation_name
    ;
 */
declare
    a varchar := 'a' || 'b';
begin
    a := 'aa';
end;

/*
unary_expression
    : ('-' | '+') unary_expression
    | quantified_expression
    | standard_function
    | atom
    | implicit_cursor_expression
    ;
 */
declare
    a int := -+-1;
    b int := 2;
begin
    a := b;
end;

/*
atom
    : bind_variable
    | constant
    | inquiry_directive
    | general_element outer_join_sign?
    | '(' subquery ')' subquery_operation_part*
    | '(' expressions ')'
    ;
 */
declare
a int := :c;
    b int := 5;
    c int := (a);
    d int := (a + b);
begin
    a := b;
end;

/*
constant
    : TIMESTAMP (quoted_string | bind_variable) (AT TIME ZONE quoted_string)?
    | INTERVAL (quoted_string | bind_variable | general_element_part) (
        YEAR
        | MONTH
        | DAY
        | HOUR
        | MINUTE
        | SECOND
    ) ('(' (UNSIGNED_INTEGER | bind_variable) (',' (UNSIGNED_INTEGER | bind_variable))? ')')? (
        TO (DAY | HOUR | MINUTE | SECOND ('(' (UNSIGNED_INTEGER | bind_variable) ')')?)
    )?
    | numeric
    | DATE quoted_string
    | quoted_string
    | NULL_
    | TRUE
    | FALSE
    | DBTIMEZONE
    | SESSIONTIMEZONE
    | MINVALUE
    | MAXVALUE
    | DEFAULT
    ;
 */

declare
a int := 5;
    b varchar := 'abc';
    c int := null;
    d boolean := true;
    e int := (a).a;
begin
    a := b;
end;

/*
  variable declare
  assignstmt
*/
declare
    a int := 2;
    b int;
begin
    b := a;
end;

/**
 and or
 */
declare
a boolean := true;
    b boolean := false;
begin
    b := a and b;
    a := a or b;
end;