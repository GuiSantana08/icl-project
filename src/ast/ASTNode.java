package ast;

import ast.operations.arithmetic.*;
import ast.operations.references.*;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import ast.value.ASTString;
import exceptions.InvalidTypeException;

public interface ASTNode {
    public interface Visitor<T,E> {
        T visit(ASTInt e, E env)        throws InvalidTypeException;
        T visit(ASTBool e, E env)       throws InvalidTypeException;
        T visit(ASTString e, E env)     throws InvalidTypeException;
        T visit(ASTNeg e, E env)        throws InvalidTypeException;
        T visit(ASTDiv e, E env)        throws InvalidTypeException;
        T visit(ASTMult e, E env)       throws InvalidTypeException;
        T visit(ASTSub e, E env)        throws InvalidTypeException;
        T visit(ASTAdd e, E env)        throws InvalidTypeException;
        T visit(ASTDiff e, E env)       throws InvalidTypeException;
        T visit(ASTLeq e, E env)        throws InvalidTypeException;
        T visit(ASTLt e, E env)         throws InvalidTypeException;
        T visit(ASTGeq e, E env)        throws InvalidTypeException;
        T visit(ASTGt e, E env)         throws InvalidTypeException;
        T visit(ASTEq e, E env)         throws InvalidTypeException;
        T visit(ASTAnd e, E env)        throws InvalidTypeException;
        T visit(ASTOr e, E env)         throws InvalidTypeException;
        T visit(ASTRef e, E env)        throws InvalidTypeException;
        T visit(ASTNot e, E env)        throws InvalidTypeException;
        T visit(ASTNew e, E env)        throws InvalidTypeException;
        T visit(ASTLet e, E env)        throws InvalidTypeException;
        T visit(ASTId e, E env)         throws InvalidTypeException;
        T visit(ASTAtrib e, E env)      throws InvalidTypeException;
    }
     <T,E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException;;
}
