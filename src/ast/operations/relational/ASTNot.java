package ast.operations.relational;

import ast.ASTType;
import exceptions.InvalidTypeException;

public class ASTNot extends ASTType {

        public ASTType expr;

        public ASTNot(ASTType expr){
            this.expr = expr;
        }

        @Override
        public <T,E> T accept(Visitor<T,E> v, E env) throws InvalidTypeException {
            return v.visit(this, env);
        }

        @Override
        public String toString(){
            return "ASTNot(" + expr + ")";
        }
}
