package ast.operations.relational;

import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTAnd extends ASTType {
    public ASTType left, right;

    public ASTAnd(ASTType e1, ASTType e2){
        this.left = e1;
        this.right = e2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }

    @Override
    public String toString(){
        return "ASTDiff(" + left + ", " + right + ")";
    }
}

