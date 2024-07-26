package com.zck.plsql.antlr.dispatch;

import com.zck.plsql.antlr.PlSqlLexer;
import com.zck.plsql.antlr.PlSqlParser;
import com.zck.plsql.antlr.PlSqlParser.Anonymous_blockContext;
import com.zck.plsql.antlr.executor.interpreter.InterpreterContext;
import com.zck.plsql.antlr.intermediate.SymTab;
import com.zck.plsql.antlr.syntax.ITreeNode;
import com.zck.plsql.antlr.syntax.PLVisitor;
import com.zck.plsql.antlr.syntax.statement.Statement;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Task {
    public ITreeNode parse(String str) {
        PlSqlLexer lexer = new PlSqlLexer(CharStreams.fromString(str));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        PlSqlParser parser = new PlSqlParser(tokenStream);
        Anonymous_blockContext tree = parser.unit_statement().anonymous_block();
        System.out.println(tree.toStringTree(parser));
        PLVisitor plVisitor = new PLVisitor(str);
        return (ITreeNode) plVisitor.visitAnonymous_block(tree);
    }

    public void semanticCheck(ITreeNode tree) throws Exception {
        SymTab symTab = new SymTab();
        tree.semanticCheck(symTab);
        StringBuffer sb = new StringBuffer();
        ((Statement) tree).toTreeString(sb, "", "");
        System.out.println(sb);
    }

    ;

    public void stepExecute(ITreeNode tree) throws Exception {
        InterpreterContext context = new InterpreterContext();
        tree.stepExec(context);
    }

    public void execute(ITreeNode tree) throws Exception {
        InterpreterContext context = new InterpreterContext();
        tree.execute(context);
    }
}
