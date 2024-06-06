package ast.value;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import type.BoolType;

public class ASTBool extends ASTNode{
    public final boolean value;

    public ASTBool(boolean value) {
        super(new BoolType());
        this.value = value;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env)  {
        return v.visit(this, env);
    }
}
