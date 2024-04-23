package ast.operations.references;

import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTRef extends ASTType {

    public ASTType mem, val;

    public ASTRef(ASTType e1, ASTType e2) {
        this.mem = e1;
        this.val = e2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException {
        return v.visit(this, env);
    }
}
