package ast.functions.io.out;

import ast.ASTNode;

public class ASTExit extends ASTNode {
    public ASTExit() {
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) {
        return null;
    }
}
