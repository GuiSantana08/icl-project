package ast.operations.relational;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTEq implements ASTNode {
    public ASTNode arg1;
    public ASTNode arg2;

    public ASTEq(ASTNode left, ASTNode right){
        arg1 = left;
        arg2 = right;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
