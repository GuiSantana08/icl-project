package ast.operations.arithmetic;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTAdd implements ASTNode {
    public ASTNode arg1;
    public ASTNode arg2;

    public ASTAdd(ASTNode arg1, ASTNode arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) {
        return v.visit(this, env);
    }
}
