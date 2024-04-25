package ast.operations.references;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTNew implements ASTNode {

    public ASTNode exp;

    public ASTNew(ASTNode exp) {
        this.exp = exp;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}

