package ast.operations.relational;

import ast.ASTValue;
import exceptions.InvalidTypeException;

public class ASTOr extends ASTValue {

    public ASTValue left;
    public ASTValue right;

    public ASTOr(ASTValue left, ASTValue right){
        this.left = left;
        this.right = right;
    }

    @Override
    public <T,E> T accept(Visitor<T,E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }

    @Override
    public String toString(){
        return "ASTOr(" + left + ", " + right + ")";
    }

}
