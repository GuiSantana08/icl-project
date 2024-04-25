package ast.value;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTBool implements ASTNode{
    public final boolean value;

    public ASTBool(boolean value) {
        this.value = value;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
