package ast;

import ast.value.ASTBool;
import ast.value.ASTInt;
import exceptions.InvalidTypeException;

public interface ASTNode {
    public interface Visitor<T,E> {
        public T visit(ASTInt i, E env) throws InvalidTypeException;
        public T visit(ASTBool b, E env) throws InvalidTypeException;

    }
    public <T,E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException;;
}
