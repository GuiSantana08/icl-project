package type;

import ast.ASTNode;
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
}
