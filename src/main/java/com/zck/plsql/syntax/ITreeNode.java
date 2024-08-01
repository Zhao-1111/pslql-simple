package com.zck.plsql.syntax;

import com.zck.plsql.executor.compiler.CompilerContext;
import com.zck.plsql.executor.interpreter.InterpreterContext;
import com.zck.plsql.intermediate.SymTab;
import com.zck.plsql.syntax.expression.Expression;
import com.zck.plsql.syntax.expression.constantExpression.ConstantExpression;
import com.zck.plsql.syntax.expression.variableExpression.VariableExpression;
import com.zck.plsql.syntax.statement.Statement;
import com.zck.plsql.syntax.statement.VariableDeclaration;

import java.util.ArrayList;
import java.util.List;

public abstract class ITreeNode {
    /**
     * 用以存放子节点
     */
    public List<ITreeNode> childrens = new ArrayList<>();

    /**
     * 语义检查
     *
     * @return
     */
    public Object semanticCheck(SymTab symTab) throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.semanticCheck(symTab);
        }
        return null;
    }

    ;

    /**
     * 单步解释执行
     *
     * @param ctx
     * @return
     * @throws Exception
     */
    public Object stepExec(InterpreterContext ctx) throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.stepExec(ctx);
        }
        return null;
    }

    /**
     * 解释执行
     *
     * @param ctx
     * @return
     * @throws Exception
     */
    public Object executeException(InterpreterContext ctx) throws Exception {
        Object obj = null;
        try {
            obj = execute(ctx);
        } catch (Exception e) {
            exceptionHandle(ctx, e);

        }
        return obj;
    }

    public Object execute(InterpreterContext ctx) throws Exception {
        for (ITreeNode node : getChildrens()) {
            node.executeException(ctx);
        }
        return null;
    }

    public void exceptionHandle(InterpreterContext ctx, Exception e) throws Exception {
        throw e;
    }

    /**
     * 编译执行前IR代码生成
     *
     * @param ctx
     * @return
     * @throws Exception
     */
    public abstract Object compileCodeGen(CompilerContext ctx) throws Exception;

    /**
     * set value
     *
     * @param key
     * @param value
     */
    public abstract void setAttribute(Object key, Object value);

    /**
     * get value by key
     *
     * @param key
     * @return
     */
    public abstract Object getAttribute(Object key);

    public List<ITreeNode> getChildrens() {
        return childrens;
    }

    /**
     * 语法树可视化
     *
     * @param sb
     * @param padding
     * @param pointer
     */
    public void toTreeString(StringBuffer sb, String padding, String pointer) {
        sb.append(padding);
        sb.append(pointer);
        String value = null;
        if (this instanceof VariableDeclaration) {
            value = ":" + ((VariableDeclaration) this).getVarExpression().getName();
        } else if (this instanceof VariableExpression) {
            value = ":" + ((VariableExpression) this).getName();
        } else if (this instanceof ConstantExpression) {
            value = ":" + ((ConstantExpression) this).getConstValue();
        }
        if (value != null) {
            sb.append(getClass().getSimpleName() + value);
        } else {
            sb.append(getClass().getSimpleName());
        }
        sb.append("\n");

        String paddingForChildren = padding + "│  ";
        String pointerForLastChild = "└──";
        String pointerForChild = "├──";

        for (int i = 0; i < childrens.size(); i++) {
            // 当前只显示statement 和 expression
            if (childrens.get(i) instanceof Statement || childrens.get(i) instanceof Expression) {
                ITreeNode child = childrens.get(i);
                if (i == childrens.size() - 1) {
                    child.toTreeString(sb, paddingForChildren, pointerForLastChild);
                } else {
                    child.toTreeString(sb, paddingForChildren, pointerForChild);
                }
            }
        }
    }

}
