package ast.operations.references;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTId implements ASTNode {

    public String id;

    public ASTId(String id) {
        this.id = id;
    }
    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
