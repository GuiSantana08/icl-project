package ast.operations.relational;

import ast.ASTValue;
import exceptions.InvalidTypeException;

public class ASTEq extends ASTValue {
    public ASTValue arg1;
    public ASTValue arg2;

    public ASTEq(ASTValue left, ASTValue right){
        arg1 = left;
        arg2 = right;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
