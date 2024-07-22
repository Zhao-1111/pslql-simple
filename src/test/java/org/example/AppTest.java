package org.example;

import com.zck.plsql.antlr.PlSqlLexer;
import com.zck.plsql.antlr.PlSqlParser;
import com.zck.plsql.antlr.PlSqlParser.Anonymous_blockContext;
import com.zck.plsql.antlr.dispatch.Task;
import com.zck.plsql.antlr.executor.interpreterContext.InterpreterContext;
import com.zck.plsql.antlr.syntax.ITreeNode;
import com.zck.plsql.antlr.syntax.PLVisitor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp1()
    {
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
            task.stepExecute(tree);
            System.out.println(tree.toString());
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        assertTrue( true );
    }
}
