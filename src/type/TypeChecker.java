package type;

import ast.ASTNode;
import ast.ASTNode.Visitor;
import ast.operations.arithmetic.*;
import ast.operations.references.*;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import exceptions.InvalidTypeException;
import symbols.Env;

public class TypeChecker implements Visitor<Type, Env<Type>>{
    @Override
    public Type visit(ASTInt i, Env<Type> env) throws InvalidTypeException {
        return IntType.singleton;
    }

    @Override
    public Type visit(ASTBool b, Env<Type> env) throws InvalidTypeException {
        return BoolType.singleton;
    }

    @Override
    public Type visit(ASTNeg e, Env<Type> env) throws InvalidTypeException {
        Type arg = e.arg.accept(this, env);
        if(arg == IntType.singleton) {
            return arg;
        } else {
            throw new InvalidTypeException("Type error in negation");
        }
    }

    @Override
    public Type visit(ASTDiv e, Env<Type> env) throws InvalidTypeException {
        return handleIntegerOperation(e.arg1, e.arg2, "division", env);
    }

    @Override
    public Type visit(ASTMult e, Env<Type> env) throws InvalidTypeException {
        return handleIntegerOperation(e.arg1, e.arg2, "multiplication", env);
    }

    @Override
    public Type visit(ASTSub e, Env<Type> env) throws InvalidTypeException {
        return handleIntegerOperation(e.arg1, e.arg2, "subtraction", env);
    }

    @Override
    public Type visit(ASTAdd e, Env<Type> env) throws InvalidTypeException {
        return handleIntegerOperation(e.arg1, e.arg2, "addition", env);
    }

    @Override
    public Type visit(ASTDiff e, Env<Type> env) throws InvalidTypeException {
        Type left = e.arg1.accept(this, env);
        Type right = e.arg2.accept(this, env);
        if (left == right) {
            return BoolType.singleton;
        } else {
            throw new InvalidTypeException("Type error in difference operation");
        }
    }

    @Override
    public Type visit(ASTEq e, Env<Type> env) throws InvalidTypeException {
        Type left = e.arg1.accept(this, env);
        Type right = e.arg2.accept(this, env);
        if (left == right) {
            return BoolType.singleton;
        } else {
            throw new InvalidTypeException("Type error in equality operation");
        }
    }

    @Override
    public Type visit(ASTLeq e, Env<Type> env) throws InvalidTypeException {
        return handleIntegerOperation(e.arg1, e.arg2, "less than or equal", env);
    }

    @Override
    public Type visit(ASTLt e, Env<Type> env) throws InvalidTypeException {
        return handleIntegerOperation(e.arg1, e.arg2, "less than", env);
    }

    @Override
    public Type visit(ASTGeq e, Env<Type> env) throws InvalidTypeException {
        return handleIntegerOperation(e.arg1, e.arg2, "greater than or equal", env);
    }
    @Override
    public Type visit(ASTGt e, Env<Type> env) throws InvalidTypeException {
        return handleIntegerOperation(e.arg1, e.arg2, "greater than", env);
    }


    @Override
    public Type visit(ASTAnd e, Env<Type> env) throws InvalidTypeException {
        return handleBooleanOperation(e.left, e.right, "and", env);
    }

    @Override
    public Type visit(ASTOr e, Env<Type> env) throws InvalidTypeException {
        return handleBooleanOperation(e.left, e.right, "or", env);
    }

    @Override
    public Type visit(ASTRef e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTNot astNot, Env<Type> env) throws InvalidTypeException {
        Type arg = astNot.arg.accept(this, env);
        if (arg == BoolType.singleton) {
            return arg;
        } else {
            throw new InvalidTypeException("Type error in negation");
        }
    }

    @Override
    public Type visit(ASTNew e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTLet e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTId e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTAtrib e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    private Type handleIntegerOperation(ASTNode arg1, ASTNode arg2, String operationName, Env<Type> env) throws InvalidTypeException {
        Type left = arg1.accept(this, env);
        Type right = arg2.accept(this, env);
        if (left == IntType.singleton && right == IntType.singleton) {
            return left;
        } else {
            throw new InvalidTypeException("Type error in " + operationName + " operation");
        }
    }

    private Type handleBooleanOperation(ASTNode arg1, ASTNode arg2, String operationName, Env<Type> env) throws InvalidTypeException {
        Type left = arg1.accept(this, env);
        Type right = arg2.accept(this, env);
        if (left == BoolType.singleton && right == BoolType.singleton) {
            return left;
        } else {
            throw new InvalidTypeException("Type error in " + operationName + " operation");
        }
    }
}
