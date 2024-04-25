package ast.operations.arithmetic;

import ast.ASTValue;
import exceptions.InvalidTypeException;

public class ASTDiv extends ASTValue{
    public final ASTValue arg1;
    public final ASTValue arg2;

    public ASTDiv(ASTValue arg1, ASTValue arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
