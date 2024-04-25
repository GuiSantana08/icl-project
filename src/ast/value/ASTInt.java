package ast.value;

import ast.ASTValue;
import exceptions.InvalidTypeException;

public class ASTInt extends ASTValue {
    public final int value;

    public ASTInt(int value) {
        this.value = value;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
