package ast.operations.references;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import type.RefType;

public class ASTDRef extends ASTNode {

    public ASTNode exp;

    public ASTDRef(ASTNode exp) {
        super(new RefType(null));
        this.exp = exp;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env){
        return v.visit(this, env);
    }
}
