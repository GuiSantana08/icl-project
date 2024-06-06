package ast.operations.references;

import ast.ASTNode;
import ast.value.ASTString;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import type.RefType;

public class ASTId extends ASTNode {

    public String id;

    public ASTId(String id) {
        super(new RefType(null));
        this.id = id;
    }
    @Override
    public <T, E> T accept(Visitor<T, E> v, E env)  {
        return v.visit(this, env);
    }
}
