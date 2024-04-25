package ast.operations.arithmetic;

import ast.ASTValue;
import exceptions.InvalidTypeException;

public class ASTNeg extends ASTValue {
    public final ASTValue arg;

    public ASTNeg(ASTValue arg) {
        this.arg = arg;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
