package com.zck.plsql.simpleplsql;

import com.zck.plsql.dispatch.Task;
import com.zck.plsql.syntax.ITreeNode;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ExpressionTest {
    @Test
    public void test0() {
        String str = "declare\n"
                + "    var1 int := 7 + 2 -3 * 7;\n"
                + "    var2 varchar := 'dfs';\n"
                + "    var3 number := 10;\n"
                + "    var4 int;\n"
                + "begin\n"
                + "    var4 := var1;\n"
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

    @Test
    public void test1() {
        String str =
                "declare\n"
                        + "    var2 boolean := true;\n"
                        + "begin\n"
                        + "    var2 := var2 and var2;\n"
                        + "end;";
        try {
            Task task = new Task();
            ITreeNode tree = task.parse(str);
            task.semanticCheck(tree);
        } catch (Exception exception) {
            exception.printStackTrace();
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void test2() {
        String str = "declare\n"
                + "    a boolean := true;\n"
                + "    b boolean := false;\n"
                + "begin\n"
                + "    b := a or b;\n"
                + "    a := false and b;\n"
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
