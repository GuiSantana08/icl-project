package ast.operations.references;

import ast.ASTNode;
import type.RefType;

public class  ASTReff extends ASTNode {

    public ASTNode id, exp;

    public ASTReff(ASTNode e1, ASTNode e2) {
        super(new RefType(e2.getASTType()));
        this.id = e1;
        this.exp = e2;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env)  {
        return v.visit(this, env);
    }
}
