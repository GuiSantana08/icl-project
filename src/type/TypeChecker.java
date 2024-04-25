package type;

import ast.ASTNode;
import ast.operations.arithmetic.*;
import ast.operations.references.ASTRef;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import exceptions.InvalidTypeException;
import symbols.Env;

public class TypeChecker implements ASTNode.Visitor<Type, Env<Type>>{
    @Override
    public Type visit(ASTInt i, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTBool b, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTNeg e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTDiv e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTMult e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTSub e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTAdd e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTDiff e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTLeq e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTLt e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTGeq e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTGt e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTEq e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTAnd e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTOr e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTRef e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTNot astNot, Env<Type> env) throws InvalidTypeException {
        return null;
    }
}
