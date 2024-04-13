package interpreter;

import ast.ASTNode;
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


    public static Type interpret(ASTNode e) throws InvalidTypeException {
        Interpreter i = new Interpreter();
        //TODO: check if this is correct
        return e.accept(i, null);

    }
}
