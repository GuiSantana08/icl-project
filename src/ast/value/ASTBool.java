package ast.value;

import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTBool extends ASTType {
    public final boolean value;

    public ASTBool(boolean value) {
        this.value = value;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
