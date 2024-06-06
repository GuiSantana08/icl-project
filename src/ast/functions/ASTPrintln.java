package ast.functions;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTPrintln extends ASTNode {
    public ASTNode exp;

    public ASTPrintln(ASTNode exp) {
        super();
        this.exp = exp;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) {
        return v.visit(this, env);
    }
}
