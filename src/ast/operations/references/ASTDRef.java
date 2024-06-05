package ast.operations.references;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTDRef implements ASTNode {

    public ASTNode exp;

    public ASTDRef(ASTNode exp) {
        this.exp = exp;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env){
        return v.visit(this, env);
    }
}
