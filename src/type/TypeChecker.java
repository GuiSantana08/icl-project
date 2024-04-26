package type;

import ast.ASTNode;
import ast.ASTNode.Visitor;
import ast.control.ASTIfThenElse;
import ast.control.ASTSeq;
import ast.control.ASTWhile;
import ast.functions.ASTPrint;
import ast.functions.ASTPrintln;
import ast.operations.arithmetic.*;
import ast.operations.references.*;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import ast.value.ASTString;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import symbols.Env;
import symbols.Tuple;
import value.RefValue;

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
    public Type visit(ASTString s, Env<Type> env) throws InvalidTypeException {
        return StringType.singleton;
    }

    @Override
    public Type visit(ASTNeg e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Type arg = e.arg.accept(this, env);
        if(arg == IntType.singleton) {
            return arg;
        } else {
            throw new InvalidTypeException("Type error in negation");
        }
    }

    @Override
    public Type visit(ASTDiv e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return handleIntegerOperation(e.arg1, e.arg2, "division", env);
    }

    @Override
    public Type visit(ASTMult e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return handleIntegerOperation(e.arg1, e.arg2, "multiplication", env);
    }

    @Override
    public Type visit(ASTSub e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return handleIntegerOperation(e.arg1, e.arg2, "subtraction", env);
    }

    @Override
    public Type visit(ASTAdd e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return handleIntegerOperation(e.arg1, e.arg2, "addition", env);
    }

    @Override
    public Type visit(ASTDiff e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Type left = e.arg1.accept(this, env);
        Type right = e.arg2.accept(this, env);
        if (left == right) {
            return BoolType.singleton;
        } else {
            throw new InvalidTypeException("Type error in difference operation");
        }
    }

    @Override
    public Type visit(ASTEq e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Type left = e.arg1.accept(this, env);
        Type right = e.arg2.accept(this, env);
        if (left == right) {
            return BoolType.singleton;
        } else {
            throw new InvalidTypeException("Type error in equality operation");
        }
    }

    @Override
    public Type visit(ASTLeq e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return handleIntegerBoolOperation(e.arg1, e.arg2, "less than or equal", env);
    }

    @Override
    public Type visit(ASTLt e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return handleIntegerBoolOperation(e.arg1, e.arg2, "less than", env);
    }

    @Override
    public Type visit(ASTGeq e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return handleIntegerBoolOperation(e.arg1, e.arg2, "greater than or equal", env);
    }
    @Override
    public Type visit(ASTGt e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return handleIntegerBoolOperation(e.arg1, e.arg2, "greater than", env);
    }


    @Override
    public Type visit(ASTAnd e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return handleBooleanOperation(e.left, e.right, "and", env);
    }

    @Override
    public Type visit(ASTOr e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return handleBooleanOperation(e.left, e.right, "or", env);
    }

    @Override
    public Type visit(ASTNot astNot, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Type arg = astNot.arg.accept(this, env);
        if (arg == BoolType.singleton) {
            return arg;
        } else {
            throw new InvalidTypeException("Type error in boolean negation");
        }
    }

    @Override
    public Type visit(ASTIfThenElse e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Type condition = e.condition.accept(this, env);
        Type thenBranch = e.thenBranch.accept(this, env);
        Type elseBranch = e.elseBranch.accept(this, env);
        if (condition == BoolType.singleton && thenBranch == elseBranch) {
            return thenBranch;
        } else {
            throw new InvalidTypeException("Type error in if-then-else operation");
        }
    }

    @Override
    public Type visit(ASTNew e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Type arg = e.exp.accept(this, env);
        return new RefType(arg);
    }

    @Override
    public Type visit(ASTLet e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        env = env.beginScope();
        for(Tuple<String, ASTNode> t : e.vars) {
            Type var = t.item2().accept(this, env);
            env.bind(t.item1(), var);
        }
        return e.body.accept(this, env);
    }

    //TODO: Implement the visit method for ASTId
    @Override
    public Type visit(ASTId e, Env<Type> env) throws InvalidTypeException {
        Type tp = env.find(e.id);
        if (tp == null)
            throw new InvalidTypeException("Variable not found");
        return tp;
    }

    @Override
    public Type visit(ASTReff e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        RefType arg1 = (RefType)e.id.accept(this, env);
        Type arg2 = e.exp.accept(this, env);
        if(arg1.getRefType().getType().equals(arg2.getType()))
            return arg1;
        else
            throw new InvalidTypeException("Type error in 'atrib'");
    }

    @Override
    public Type visit(ASTWhile e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Type condition = e.condition.accept(this, env);
        Type body = e.body.accept(this, env);
        if (condition != BoolType.singleton) {
            throw new InvalidTypeException("Type error in while operation");
        }
        return null;
    }

    @Override
    public Type visit(ASTSeq e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Type visit(ASTDRef e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        RefType type = (RefType) e.exp.accept(this, env);
        if (type == null)
            throw new InvalidTypeException("Variable not found");
        return type;
    }

    @Override
    public Type visit(ASTPrint e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return e.exp.accept(this, env);
    }

    @Override
    public Type visit(ASTPrintln e, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return e.exp.accept(this, env);
    }

    private Type handleIntegerOperation(ASTNode arg1, ASTNode arg2, String operationName, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Type left = arg1.accept(this, env);
        Type right = arg2.accept(this, env);
        if (left == IntType.singleton && right == IntType.singleton) {
            return left;
        } else {
            throw new InvalidTypeException("Type error in " + operationName + " operation");
        }
    }

    private Type handleIntegerBoolOperation(ASTNode arg1, ASTNode arg2, String operationName, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Type left = arg1.accept(this, env);
        Type right = arg2.accept(this, env);
        if (left == IntType.singleton && right == IntType.singleton) {
            return BoolType.singleton;
        } else {
            throw new InvalidTypeException("Type error in " + operationName + " operation");
        }
    }

    private Type handleBooleanOperation(ASTNode arg1, ASTNode arg2, String operationName, Env<Type> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Type left = arg1.accept(this, env);
        Type right = arg2.accept(this, env);
        if (left == BoolType.singleton && right == BoolType.singleton) {
            return BoolType.singleton;
        } else {
            throw new InvalidTypeException("Type error in " + operationName + " operation");
        }
    }
}
