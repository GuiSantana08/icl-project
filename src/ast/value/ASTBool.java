package ast.value;

import ast.ASTValue;
import exceptions.InvalidTypeException;

public class ASTBool extends ASTValue {
    public final boolean value;

    public ASTBool(boolean value) {
        this.value = value;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
