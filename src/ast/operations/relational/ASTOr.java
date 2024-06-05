package ast.operations.relational;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTOr implements ASTNode {

    public ASTNode left;
    public ASTNode right;

    public ASTOr(ASTNode left, ASTNode right){
        this.left = left;
        this.right = right;
    }

    @Override
    public <T,E> T accept(Visitor<T,E> v, E env)  {
        return v.visit(this, env);
    }

    @Override
    public String toString(){
        return "ASTOr(" + left + ", " + right + ")";
    }

}
