package ast.operations.references;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import type.RefType;

public class ASTNew extends ASTNode {

    public ASTNode exp;

    public ASTNew(ASTNode exp) {
        super(new RefType(null));
        this.exp = exp;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
        return v.visit(this, env);
    }
}

