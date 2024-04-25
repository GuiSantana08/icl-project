package ast.operations.references;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTRef implements ASTNode {

    public ASTNode val;

    public ASTRef() {

    }

    public ASTRef(ASTNode val) {
        this.val = val;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
