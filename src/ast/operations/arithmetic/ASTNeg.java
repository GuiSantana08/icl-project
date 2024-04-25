package ast.operations.arithmetic;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTNeg implements ASTNode {
    public final ASTNode arg;

    public ASTNeg(ASTNode arg) {
        this.arg = arg;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
