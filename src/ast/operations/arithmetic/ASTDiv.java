package ast.operations.arithmetic;

import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTDiv extends ASTType{
    public final ASTType arg1;
    public final ASTType arg2;

    public ASTDiv(ASTType arg1, ASTType arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
