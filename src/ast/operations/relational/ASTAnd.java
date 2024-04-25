package ast.operations.relational;

import ast.ASTValue;
import exceptions.InvalidTypeException;

public class ASTAnd extends ASTValue {
    public ASTValue left, right;

    public ASTAnd(ASTValue e1, ASTValue e2){
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

