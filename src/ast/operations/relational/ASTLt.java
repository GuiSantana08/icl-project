package ast.operations.relational;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTLt implements ASTNode {
    public ASTNode arg1;
    public ASTNode arg2;

    public ASTLt(ASTNode arg1, ASTNode arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
