package ast.functions;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTPrint extends ASTNode {

        public ASTNode exp;

        public ASTPrint(ASTNode exp) {
            super();
            this.exp = exp;
        }

        @Override
        public <T, E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
            return v.visit(this, env);
        }
}
