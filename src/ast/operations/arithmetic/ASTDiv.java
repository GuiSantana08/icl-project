package ast.operations.arithmetic;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTDiv implements ASTNode {
    public final ASTNode arg1;
    public final ASTNode arg2;

    public ASTDiv(ASTNode arg1, ASTNode arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
