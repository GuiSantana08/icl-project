package ast.value;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import type.StringType;

public class ASTString extends ASTNode {


    public final String string;

    public ASTString(String string) {
        super(new StringType());
        this.string = string;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) {
        return v.visit(this, env);
    }
}
