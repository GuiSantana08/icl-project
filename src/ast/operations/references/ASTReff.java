package ast.operations.references;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class  ASTReff implements ASTNode {

    public ASTNode id, exp;

    public ASTReff(ASTNode e1, ASTNode e2) {
        this.id = e1;
        this.exp = e2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
        return v.visit(this, env);
    }
}
