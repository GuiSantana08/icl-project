package compiler;

import ast.value.ASTBool;
import ast.value.ASTInt;
import exceptions.InvalidTypeException;
import symbols.Env;
import type.Type;

public class CodeGen implements ast.ASTNode.Visitor<Void, Env<Type>>{
    @Override
    public Void visit(ASTInt i, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTBool b, Env<Type> env) throws InvalidTypeException {
        return null;
    }
}
