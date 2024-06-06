package ast.operations.relational;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import type.BoolType;

public class ASTGt extends ASTNode {
    public ASTNode arg1;
    public ASTNode arg2;

    public ASTGt(ASTNode arg1, ASTNode arg2) {
        super(new BoolType());
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) {
        return v.visit(this, env);
    }
}
