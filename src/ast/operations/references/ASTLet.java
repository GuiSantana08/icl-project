package ast.operations.references;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTLet implements ASTNode {

    public ASTNode arg1, arg2;

    public ASTLet(ASTNode exp1, ASTNode exp2) {
        this.arg1 = exp1;
        this.arg2 = exp2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}

