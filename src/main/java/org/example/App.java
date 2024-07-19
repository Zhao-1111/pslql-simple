package org.example;

import com.zck.plsql.antlr.PlSqlLexer;
import com.zck.plsql.antlr.PlSqlParser;
import com.zck.plsql.antlr.PlSqlParser.Anonymous_blockContext;
import com.zck.plsql.antlr.syntax.ITreeNode;
import com.zck.plsql.antlr.executor.interpreterContext.InterpreterContext;
import com.zck.plsql.antlr.syntax.PLVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Hello world!
 * 1. 对外解析
 */
public class App 
{
    public static void main( String[] args )
    {
        String str = "declare\n"
                + "    var1 int := 7;\n"
                + "    var2 varchar(20) := 'dfs';\n"
                + "    var3 number := 10;\n"
                + "    var4 int;\n"
                + "begin\n"
                + "    var4 := var1;\n"
                + "    select column1, column2 into var3, var2 from tab1;\n"
                + "end;";
        try {
            PlSqlLexer lexer = new PlSqlLexer(CharStreams.fromString(str));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            PlSqlParser parser = new PlSqlParser(tokenStream);
            ParseTree tree = parser.unit_statement().anonymous_block();
            PLVisitor plVisitor = new PLVisitor(str);
            ITreeNode plTree = (ITreeNode) plVisitor.visitAnonymous_block((Anonymous_blockContext) tree);
            plTree.semanticCheck();
            InterpreterContext context = new InterpreterContext();
            plTree.execute(context);
            System.out.println(tree.toStringTree(parser));
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}


