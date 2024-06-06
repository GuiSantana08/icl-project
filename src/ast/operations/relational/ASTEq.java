package ast.operations.relational;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import type.BoolType;

public class ASTEq extends ASTNode {
    public ASTNode arg1;
    public ASTNode arg2;

    public ASTEq(ASTNode left, ASTNode right){
        super(new BoolType());
        arg1 = left;
        arg2 = right;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env)  {
        return v.visit(this, env);
    }
}
