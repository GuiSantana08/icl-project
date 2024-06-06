package ast.operations.relational;

import ast.ASTNode;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import type.BoolType;

public class ASTNot extends ASTNode {

        public ASTNode arg;

        public ASTNot(ASTNode arg){

            super(new BoolType());
            this.arg = arg;
        }

        @Override
        public <T,E> T accept(Visitor<T,E> v, E env)  {
            return v.visit(this, env);
        }

        @Override
        public String toString(){
            return "ASTNot(" + arg + ")";
        }
}
