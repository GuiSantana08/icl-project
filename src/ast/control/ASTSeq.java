package ast.control;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTSeq extends ASTNode {
    public ASTNode left;
    public ASTNode right;

    public ASTSeq(ASTNode left, ASTNode right) {
        super();
        this.left = left;
        this.right = right;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) {
        return v.visit(this, env);
    }
}
