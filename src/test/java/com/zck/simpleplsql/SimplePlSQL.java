package com.zck.simpleplsql;

import com.zck.plsql.dispatch.Task;
import com.zck.plsql.syntax.ITreeNode;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SimplePlSQL {
    @Test
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertTrue(true);
    }

    @Test
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
    @Test
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
            if (exception.getMessage().equals("type exception")) {
                assertTrue(true);
                return;
            } else {
                assertTrue(false);
            }
        }
        assertTrue(false);
    }

    @Test
    public void testApp4() {
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
                + "   var2 int := 8;\n"
                + " begin\n"
                + "   var1 := var2;\n"
                + "end;";
        try {
            Task task = new Task();
            ITreeNode tree = task.parse(str);
            task.semanticCheck(tree);
            task.execute(tree);
        } catch (Exception exception) {
            exception.printStackTrace();
            assertTrue(false);
        }
        assertTrue(true);
    }

}
