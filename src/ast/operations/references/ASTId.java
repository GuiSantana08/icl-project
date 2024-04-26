package ast.operations.references;

import ast.ASTNode;
import ast.value.ASTString;
import exceptions.InvalidTypeException;

public class ASTId implements ASTNode {

    public ASTString id;

    public ASTId(ASTString id) {
        this.id = id;
    }
    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
