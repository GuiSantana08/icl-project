package ast.operations.references;

import ast.ASTNode;

public class ASTDRef extends ASTNode {

    public ASTNode exp;

    public ASTDRef(ASTNode exp) {
        super(exp.getASTType());
        this.exp = exp;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env){
        return v.visit(this, env);
    }
}
