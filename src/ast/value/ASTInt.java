package ast.value;

import ast.ASTNode;
import type.IntType;


public class ASTInt extends ASTNode {
    public final int value;

    public ASTInt(int value) {
        super(new IntType());
        this.value = value;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env)  {
        return v.visit(this, env);
    }
}
