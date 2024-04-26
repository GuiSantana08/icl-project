package ast;

import ast.control.ASTIfThenElse;
import ast.control.ASTSeq;
import ast.control.ASTWhile;
import ast.operations.arithmetic.*;
import ast.operations.references.*;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;

public interface ASTNode {
    public interface Visitor<T,E> {
        T visit(ASTInt e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTBool e, E env)       throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTNeg e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTDiv e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTMult e, E env)       throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTSub e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTAdd e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTDiff e, E env)       throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTLeq e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTLt e, E env)         throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTGeq e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTGt e, E env)         throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTEq e, E env)         throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTAnd e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTOr e, E env)         throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTRef e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTNot e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTIfThenElse e, E env) throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTNew e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTLet e, E env)        throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTId e, E env)         throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTAtrib e, E env)      throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTWhile e, E env)      throws InvalidTypeException, DuplicateVariableFoundException;
        T visit(ASTSeq e, E env) throws InvalidTypeException, DuplicateVariableFoundException;
    }
     <T,E> T accept(Visitor<T, E> v, E env) throws InvalidTypeException, DuplicateVariableFoundException;;
}
