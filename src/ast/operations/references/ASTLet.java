package ast.operations.references;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import symbols.Tuple;
import type.RefType;

import java.util.List;

public class ASTLet extends ASTNode {

    public List<Tuple<String, ASTNode>> vars;
    public ASTNode body;

    public ASTLet(List<Tuple<String, ASTNode>> vars, ASTNode body) {
        super(new RefType(null));
        this.vars = vars;
        this.body = body;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env)  {
        return v.visit(this, env);
    }
}

