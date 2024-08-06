package com.zck.plsql.intermediate.type;

import java.util.Arrays;
import java.util.List;

/**
 * Reference Documents
 * <a href="https://docs.oracle.com/en/database/oracle/oracle-database/19/lnpls/plsql-data-types.html#GUID-391C58FD-16AF-486C-AF28-173E309CDBA5">...</a>
 * According to Oracle's type definition, but not entirely implemented according to it.
 * The following annotations are also for reference only
 */
public enum Type {
    /**
     * Variable-length character string having maximum length size bytes or characters.
     * You must specify size for VARCHAR2. Minimum size is 1 byte or 1 character.
     * Maximum size is: 32767
     */
    VARCHAR2("VARCHAR2", "VARCHAR", "STRING"),
    /**
     * Variable-length Unicode character string having maximum length size characters.
     * You must specify size for NVARCHAR2.
     * The number of bytes can be up to two times size for AL16UTF16 encoding and three times size for UTF8 encoding.
     * Maximum size is determined by the national character set definition, with an upper limit of: 32767
     */
    NVARCHAR2("NVARCHAR2"),
    /**
     * Number having precision p and scale s.
     * The precision p can range from 1 to 38.
     * The scale s can range from -84 to 127.
     * Both precision and scale are in decimal digits.
     * A NUMBER value requires from 1 to 22 bytes.
     */
    NUMBER("DEC", "NUMERIC", "NUMBER", "DECIMAL"),
    /**
     * A subtype of the NUMBER data type having precision p.
     * A FLOAT value is represented internally as NUMBER.
     * The precision p can range from 1 to 126 binary digits.
     * A FLOAT value requires from 1 to 22 bytes.
     */
    FLOAT("FLOAT"),
    /**
     * Character data of variable length up to 2 gigabytes, or 32767 bytes.
     * Provided for backward compatibility.
     */
    LONG("LONG"),
    /**
     * Valid date range from January 1, 4712 BC, to December 31, 9999 AD.
     * The default format is determined explicitly by the NLS_DATE_FORMAT parameter or implicitly by the NLS_TERRITORY parameter.
     * The size is fixed at 7 bytes.
     * This data type contains the datetime fields YEAR, MONTH, DAY, HOUR, MINUTE, and SECOND.
     * It does not have fractional seconds or a time zone.
     */
    DATE("DATE"),
    /**
     * 32-bit floating point number. This data type requires 4 bytes.
     */
    BINARY_FLOAT("BINARY_FLOAT", "REAL"),
    /**
     * 64-bit floating point number. This data type requires 8 bytes.
     */
    BINARY_DOUBLE("BINARY_DOUBLE", "DOUBLE"),
    /**
     * Year, month, and day values of date, as well as hour, minute, and second values of time,
     * where fractional_seconds_precision is the number of digits in the fractional part of the SECOND datetime field.
     * Accepted values of fractional_seconds_precision are 0 to 9.
     * The default is 6.
     * The default format is determined explicitly by the NLS_TIMESTAMP_FORMAT parameter or implicitly by the NLS_TERRITORY parameter.
     * The size is 7 or 11 bytes, depending on the precision.
     * This data type contains the datetime fields YEAR, MONTH, DAY, HOUR, MINUTE, and SECOND.
     * It contains fractional seconds but does not have a time zone.
     */
    TIMESTAMP("TIMESTAMP"),
    /**
     * All values of TIMESTAMP as well as time zone displacement value,
     * where fractional_seconds_precision is the number of digits in the fractional part of the SECOND datetime field.
     * Accepted values are 0 to 9.
     * The default is 6.
     * The default date format for the TIMESTAMP WITH TIME ZONE data type is determined by the NLS_TIMESTAMP_TZ_FORMAT initialization parameter.
     * The size is fixed at 13 bytes.
     * This data type contains the datetime fields YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, TIMEZONE_HOUR, and TIMEZONE_MINUTE.
     * It has fractional seconds and an explicit time zone.
     */
    TIMESTAMP_WITH_TIME_ZONE,
    /**
     * Stores a period of time in years and months, where year_precision is the number of digits in the YEAR datetime field.
     * Accepted values are 0 to 9. The default is 2. The size is fixed at 5 bytes.
     */
    INTERVAL_YEAR_TO_MONTH,
    /**
     * Stores a period of time in days, hours, minutes, and seconds, where
     * ·day_precision is the maximum number of digits in the DAY datetime field. Accepted values are 0 to 9. The default is 2.
     * ·fractional_seconds_precision is the number of digits in the fractional part of the SECOND field. Accepted values are 0 to 9. The default is 6.
     * The size is fixed at 11 bytes.
     */
    INTERVAL_DAY_TO_SECOND,
    /**
     * Raw binary data of length size bytes. You must specify size for a RAW value. Maximum size is: 32767
     */
    RAW("RAW"),
    /**
     * Raw binary data of variable length up to 32767
     */
    LONG_ROW("LONG ROW"),
    /**
     * Base 64 string representing the unique address of a row in its table.
     * This data type is primarily for values returned by the ROWID pseudocolumn.
     */
    ROWID("ROWID"),
    /**
     * Base 64 string representing the logical address of a row of an index-organized table.
     * The optional size is the size of a column of type UROWID.
     * The maximum size and default is 4000 bytes.
     */
    UROWID("UROWID"),
    /**
     * Fixed-length character data of length size bytes or characters.
     * Maximum size is 2000 bytes or characters. Default and minimum size is 1 byte.
     * BYTE and CHAR have the same semantics as for VARCHAR2.
     */
    CHAR("CHAR", "CHARACTER"),
    /**
     * Fixed-length character data of length size characters.
     * The number of bytes can be up to two times size for AL16UTF16 encoding and three times size for UTF8 encoding.
     * Maximum size is determined by the national character set definition, with an upper limit of 2000 bytes.
     * Default and minimum size is 1 character.
     */
    NCHAR("NCHAR"),
    /**
     * A character large object containing single-byte or multibyte characters.
     * Both fixed-width and variable-width character sets are supported, both using the database character set.
     * Maximum size is 128TB.
     */
    CLOB("CLOB"),
    /**
     * A character large object containing Unicode characters.
     * Both fixed-width and variable-width character sets are supported, both using the database national character set.
     * Maximum size is 128TB. Stores national character set data.
     */
    NCLOB("NCLOB"),
    /**
     * A binary large object. Maximum size is 128TB.
     */
    BLOB("BLOB"),
    /**
     * Contains a locator to a large binary file stored outside the database.
     * Enables byte stream I/O access to external LOBs residing on the database server.
     * Maximum size is 4 gigabytes.
     */
    BFILE("BFILE"),
    /**
     * BOOLEAN stores logical values, which are the boolean values TRUE and FALSE and the value NULL.
     * NULL represents an unknown value.
     */
    BOOLEAN("BOOLEAN"),
    /**
     * The PLS_INTEGER data type stores signed integers in the range -2,147,483,648 through 2,147,483,647, represented in 32 bits.
     */
    PLS_INTEGER("BINARY_INTEGER", "PLS_INTEGER", "NATURAL", "NATURALN", "POSITIVE", "POSITIVEN", "SIGNTYPE",
            "SIMPLE_INTEGER", "INTEGER", "INT", "SMALLINT"),

    /**
     * Not currently supported
     * For expression operations, errors should not be reported.
     * And the return value type that has not been implemented should be assigned to that type
     */
    NONSUPPORT("NONSUPPORT"),

    /**
     * Error Type
     * For expression operations, when an error should be reported, assign this type
     */
    ERROR("ERROR"),

    /**
     * Null type
     */
    NULLTYPE("NULL");
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
