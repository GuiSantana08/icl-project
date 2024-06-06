package ast.control;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTWhile extends ASTNode {
    public ASTNode condition;
    public ASTNode body;

    public ASTWhile(ASTNode condition, ASTNode body) {
        super();
        this.condition = condition;
        this.body = body;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) {
        return v.visit(this, env);
    }
}
