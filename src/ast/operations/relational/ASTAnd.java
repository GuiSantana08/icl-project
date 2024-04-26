package ast.operations.relational;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTAnd implements ASTNode {
    public ASTNode left, right;

    public ASTAnd(ASTNode e1, ASTNode e2){
        this.left = e1;
        this.right = e2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
        return v.visit(this, env);
    }

    @Override
    public String toString(){
        return "ASTDiff(" + left + ", " + right + ")";
    }
}

