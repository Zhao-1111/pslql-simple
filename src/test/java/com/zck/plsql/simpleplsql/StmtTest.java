package com.zck.plsql.simpleplsql;

import com.zck.plsql.dispatch.Task;
import com.zck.plsql.syntax.ITreeNode;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class StmtTest {
    // variabledeclare and assign stmt
    @Test
    public void test0() {
        String str = "declare\n"
                + "    a int := 2;\n"
                + "    b int;\n"
                + "begin\n"
                + "    b := a;\n"
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
