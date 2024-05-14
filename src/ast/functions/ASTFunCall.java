package ast.functions;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

import java.util.List;

public class ASTFunCall implements ASTNode {
    public ASTNode node;
    public List<ASTNode> args;

    public ASTFunCall(ASTNode node, List<ASTNode> args) {
        this.node = node;
        this.args = args;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
        return v.visit(this, env);
    }
}
