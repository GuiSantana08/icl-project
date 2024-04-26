package ast.operations.references;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTAtrib implements ASTNode {

    public ASTNode arg1, arg2;

    public ASTAtrib(ASTNode e1, ASTNode e2) {
        this.arg1 = e1;
        this.arg2 = e2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
        return v.visit(this, env);
    }
}
