package ast.operations.relational;

import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTEq extends ASTType {
    public ASTType left;
    public ASTType right;

    public ASTEq(ASTType left, ASTType right){
        this.left = left;
        this.right = right;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
