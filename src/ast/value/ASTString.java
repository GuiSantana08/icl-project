package ast.value;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTString implements ASTNode {

    public final String string;

    public ASTString(String string) {
        this.string = string;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
