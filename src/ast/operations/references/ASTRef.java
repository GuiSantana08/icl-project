package ast.operations.references;

import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTRef extends ASTType {

    public ASTType val;

    public ASTRef() {

    }

    public ASTRef(ASTType val) {
        this.val = val;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
