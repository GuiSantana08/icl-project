package ast.operations.arithmetic;

import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTNeg extends ASTType {
    public final ASTType arg;

    public ASTNeg(ASTType arg) {
        this.arg = arg;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
