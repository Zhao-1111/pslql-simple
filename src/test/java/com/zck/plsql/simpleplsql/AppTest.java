package com.zck.plsql.simpleplsql;

import com.zck.plsql.dispatch.Task;
import com.zck.plsql.syntax.ITreeNode;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp1() {
        /*
        declare
            var1 int := 7;
            var2 varchar := 'dfs';
            var3 number := 10;
            var4 int;
        begin
            var4 := var1;
            select column1, column2 into var3, var2 from tab1;
        end;
         */
        String str = "declare\n"
                + "    var1 int := 7;\n"
                + "    var2 varchar := 'dfs';\n"
                + "    var3 number := 10;\n"
                + "    var4 int;\n"
                + "begin\n"
                + "    var4 := var1;\n"
                + "    select column1, column2 into var3, var2 from tab1;\n"
                + "end;";
        try {
            Task task = new Task();
            ITreeNode tree = task.parse(str);
            task.semanticCheck(tree);
            task.execute(tree);
            System.out.println(tree.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertTrue(true);
    }

    // variable redefine check
    public void testApp2() {
        /*
        declare
            var1 int := 7;
            var1 varchar := 'dfs';
        begin
            var1 := 1;
        end;
         */
        String str = "declare\n"
                + "   var1 int := 7;\n"
                + "   var1 varchar := 'dfs';\n"
                + " begin\n"
                + "   var1 := 1;\n"
                + "end;";
        try {
            Task task = new Task();
            ITreeNode tree = task.parse(str);
            task.semanticCheck(tree);
        } catch (Exception exception) {
            exception.printStackTrace();
            if (exception.getMessage().equals("redefine variable")) {
                assertTrue(true);
                return;
            } else {
                assertTrue(false);
            }
        }
        assertTrue(false);
    }

    // type  check
    public void testApp3() {
        /*
        declare
            var1 int := 7;
            var2 varchar := 'dfs';
        begin
            var1 := var2;
        end;
         */
        String str = "declare\n"
                + "   var1 int := 7;\n"
                + "   var2 varchar := 'dfs';\n"
                + " begin\n"
                + "   var1 := var2;\n"
                + "end;";
        try {
            Task task = new Task();
            ITreeNode tree = task.parse(str);
            task.semanticCheck(tree);
        } catch (Exception exception) {
            exception.printStackTrace();
            if (exception.getMessage().equals("type exception")) {
                assertTrue(true);
                return;
            } else {
                assertTrue(false);
            }
        }
        assertTrue(false);
    }

}
