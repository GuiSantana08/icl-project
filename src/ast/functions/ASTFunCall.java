package ast.functions;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

import java.util.List;

public class ASTFunCall extends ASTNode {
    public ASTNode node;
    public List<ASTNode> args;

    public ASTFunCall(ASTNode node, List<ASTNode> args) {
        super();
        this.node = node;
        this.args = args;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) {
        return v.visit(this, env);
    }
}
