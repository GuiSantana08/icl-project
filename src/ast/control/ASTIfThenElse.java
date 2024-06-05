package ast.control;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTIfThenElse implements ASTNode {
    public ASTNode condition;
    public ASTNode thenBranch;
    public ASTNode elseBranch;

    public ASTIfThenElse(ASTNode condition, ASTNode thenBranch, ASTNode elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env)  {
        return v.visit(this, env);
    }
}
