package ast.functions;

import ast.ASTNode;
import symbols.Tuple;
import type.ClosureType;

import java.util.List;

public class ASTDefFun extends ASTNode {
    public final List<Tuple<String, String>> params; // List of (param, type) pairs
    public final ASTNode body;


    public ASTDefFun(List<Tuple<String, String>> params, ASTNode body) {
        super(new ClosureType(body.getASTType(), null)); //TODO: check if this ok
        this.params = params;
        this.body = body;
    }

    public ASTNode getBody() {
        return body;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) {
        return v.visit(this, env);
    }

}
