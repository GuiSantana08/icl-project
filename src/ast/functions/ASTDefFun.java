package ast.functions;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import symbols.Tuple;

import java.util.List;

public class ASTDefFun implements ASTNode {
    public final List<Tuple<String, String>> params; // List of (param, type) pairs
    public final ASTNode body;


    public ASTDefFun(List<Tuple<String, String>> params, ASTNode body) {
        this.params = params;
        this.body = body;
    }

    public ASTNode getBody() {
        return body;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
        return v.visit(this, env);
    }

}
