package ast.value;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTInt  implements ASTNode {
    public final int value;

    public ASTInt(int value) {
        this.value = value;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env)  {
        return v.visit(this, env);
    }
}
