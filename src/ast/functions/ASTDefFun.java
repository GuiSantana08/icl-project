package ast.functions;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

import java.util.List;

public class ASTDefFun implements ASTNode {
    public List<String> params;
    public ASTNode body;

    public ASTDefFun(List<String> params, ASTNode body) {
        this.params = params;
        this.body = body;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
        return v.visit(this, env);
    }

}
