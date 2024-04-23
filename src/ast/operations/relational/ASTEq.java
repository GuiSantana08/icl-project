package ast.operations.relational;

import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTEq extends ASTType {
    public ASTType arg1;
    public ASTType arg2;

    public ASTEq(ASTType left, ASTType right){
        arg1 = left;
        arg2 = right;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
