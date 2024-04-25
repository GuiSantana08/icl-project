package ast.operations.arithmetic;

import ast.ASTValue;
import exceptions.InvalidTypeException;

public class ASTMult extends ASTValue {
    public ASTValue arg1;
    public ASTValue arg2;

    public ASTMult(ASTValue arg1, ASTValue arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
