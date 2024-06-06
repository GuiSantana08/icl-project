package ast.operations.arithmetic;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import type.IntType;

public class ASTNeg extends ASTNode {
    public final ASTNode arg;

    public ASTNeg(ASTNode arg) {
        super(new IntType());
        this.arg = arg;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
        return v.visit(this, env);
    }
}
