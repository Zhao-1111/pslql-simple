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
            // task.execute(tree);
        } catch (Exception exception) {
            exception.printStackTrace();
            assertTrue(false);
        }
        assertTrue(true);
    }
}
