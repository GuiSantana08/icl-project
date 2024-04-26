package ast.operations.relational;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public class ASTNot implements ASTNode {

        public ASTNode arg;

        public ASTNot(ASTNode arg){
            this.arg = arg;
        }

        @Override
        public <T,E> T accept(Visitor<T,E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException {
            return v.visit(this, env);
        }

        @Override
        public String toString(){
            return "ASTNot(" + arg + ")";
        }
}
