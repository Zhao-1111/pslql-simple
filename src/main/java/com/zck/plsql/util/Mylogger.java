package com.zck.plsql.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mylogger {
    private static Logger logger = LoggerFactory.getLogger(Mylogger.class);
    /**
     * 方法所在的堆栈
     */
    private static final int LEVEL = 3;

    private static String getInfos() {
        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
        return traceElements[LEVEL].getClassName() + "."
                + traceElements[LEVEL].getMethodName() + ":"
                + traceElements[LEVEL].getLineNumber() + " ";
    }

    public static void info(String string) {
        logger.info(getInfos() + string);
    }

    public static void debug(String string) {
        logger.debug(string);
    }

    public static void error(String string) {
        logger.error(string);
    }
}

