package ast.value;

import ast.ASTNode;
import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTInt extends ASTType {
    public final int value;

    public ASTInt(int value) {
        this.value = value;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
