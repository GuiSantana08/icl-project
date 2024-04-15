package ast.operations.relational;

import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTDiff extends ASTType {
    public ASTType arg1;
    public ASTType arg2;

    public ASTDiff(ASTType arg1, ASTType arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
