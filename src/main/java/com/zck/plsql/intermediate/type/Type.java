package com.zck.plsql.intermediate.type;

import com.zck.plsql.intermediate.operator.ConcatenationOperator;

import java.util.Arrays;
import java.util.List;

public enum Type {
    // 通用的数值类型，可以存储整数和小数。格式为 NUMBER(p, s)，其中 p 是总精度，s 是小数位数。
    NUMBER("DEC", "NUMERIC", "NUMBER", "DECIMAL", "LONG"),
    // 32位的单精度浮点数类型，存储小数和科学计数法的数值。
    BINARY_FLOAT("BINARY_FLOAT", "FLOAT", "REAL"),
    // 64位的双精度浮点数类型，精度高于 BINARY_FLOAT。
    BINARY_DOUBLE("BINARY_DOUBLE", "DOUBLE"),
    // 存储有符号整数，性能优于 NUMBER，范围为 -2,147,483,648 到 2,147,483,647。
    PLS_INTEGER("PLS_INTEGER", "NATURAL", "NATURALN", "POSITIVE", "POSITIVEN", "INTEGER",
            "INT", "SMALLINT"),
    // 类似 PLS_INTEGER，但已废弃，不建议使用
    BINARY_INTEGER("BINARY_INTEGER", "SIMPLE_INTEGER"),

    // 固定长度的字符类型，长度在 1 到 2000 字节之间。
    CHAR("NCHAR", "CHAR", "CHARACTER"),
    // 可变长度的字符类型，长度在 1 到 4000 字节之间。
    VARCHAR2("NVARCHAR2", "VARCHAR2", "VARCHAR", "STRING"),
    CLOB("CLOB"), // 用于存储大文本数据的字符大对象，最大存储量为 4 GB。

    // Date Type
    DATE("DATE"), // 存储日期和时间，精确到秒，范围从公元前 4712 年 1 月 1 日到公元 9999 年 12 月 31 日。
    TIMESTAMP("TIMESTAMP"), // 存储日期和时间，精确到小数秒。
    INTERVAL("INTERVAL"), // 表示两个日期之间的间隔时间

    // Boolean type
    BOOLEAN("BOOLEAN"), // 存储 TRUE、FALSE 或 NULL。仅在 PL/SQL 中可用，不支持数据库表列类型。

    NULL;

    private final List<String> operatorStrings;

    Type(String... operatorStrings) {
        this.operatorStrings = Arrays.asList(operatorStrings);
    }

    public List<String> getOperatorStrings() {
        return operatorStrings;
    }

    public static Type fromString(String input) {
        for (Type element : Type.values()) {
            if (element.operatorStrings.contains(input.toUpperCase())) {
                return element;
            }
        }
        throw new IllegalArgumentException("No enum constant associated with input string: " + input);
    }
}
