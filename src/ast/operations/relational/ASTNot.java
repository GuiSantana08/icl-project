package ast.operations.relational;

import ast.ASTNode;
import exceptions.InvalidTypeException;

public class ASTNot implements ASTNode {

        public ASTNode expr;

        public ASTNot(ASTNode expr){
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
