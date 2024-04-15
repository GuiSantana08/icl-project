package interpreter;

import ast.ASTNode;
import ast.operations.arithmetic.*;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import exceptions.InvalidTypeException;
import symbols.Env;
import type.Type;

public class Interpreter implements ast.ASTNode.Visitor<Type, Env<Type>>{

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


    public static Type interpret(ASTNode e) throws InvalidTypeException {
        Interpreter i = new Interpreter();
        //TODO: check if this is correct because of env
        return e.accept(i, null);

    }
}
