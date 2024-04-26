package ast.operations.references;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import symbols.Tuple;

import java.util.List;

public class ASTLet implements ASTNode {

    public List<Tuple<String, ASTNode>> vars;
    public ASTNode body;

    public ASTLet(List<Tuple<String, ASTNode>> vars, ASTNode body) {
        this.vars = vars;
        this.body = body;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
        return v.visit(this, env);
    }
}

