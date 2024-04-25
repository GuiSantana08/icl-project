package ast.operations.references;

import ast.ASTValue;
import exceptions.InvalidTypeException;

public class ASTRef extends ASTValue {

    public ASTValue val;

    public ASTRef() {

    }

    public ASTRef(ASTValue val) {
        this.val = val;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
