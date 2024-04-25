package interpreter;

import ast.ASTNode;
import ast.operations.arithmetic.*;
import ast.operations.references.ASTRef;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import exceptions.InvalidTypeException;
import symbols.Env;
import value.BoolValue;
import value.IntValue;
//import value.RefValue;
import value.Value;

public class Interpreter implements ast.ASTNode.Visitor<Value<?>, Env<Value<?>{

    @Override
    public Value<?> visit(ASTInt i, Env<Value<?>> env) throws InvalidTypeException {
        return i.accept(this, env);
    }

    @Override
    public Value<?> visit(ASTBool b, Env<Value<?>> env) throws InvalidTypeException {
        return new BoolValue(b.value);
    }

    @Override
    public Value<?> visit(ASTNeg e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue n1 = (IntValue) e.arg.accept(this, env);
        return (new IntValue(-n1.getValue()));
    }

    @Override
    public Value<?> visit(ASTDiv e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue n1 = (IntValue) e.arg1.accept(this, env);
        IntValue n2 = (IntValue) e.arg2.accept(this, env);
        return (new IntValue(n1.getValue() / n2.getValue()));
    }

    @Override
    public Value<?> visit(ASTMult e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue n1 = (IntValue) e.arg1.accept(this, env);
        IntValue n2 = (IntValue) e.arg2.accept(this, env);
        return (new IntValue(n1.getValue() * n2.getValue()));
    }

    @Override
    public Value<?> visit(ASTSub e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue n1 = (IntValue) e.arg1.accept(this, env);
        IntValue n2 = (IntValue) e.arg2.accept(this, env);
        return (new IntValue(n1.getValue() - n2.getValue()));
    }

    @Override
    public Value<?> visit(ASTAdd e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue n1 = (IntValue) e.arg1.accept(this, env);
        IntValue n2 = (IntValue) e.arg2.accept(this, env);
        return (new IntValue(n1.getValue() + n2.getValue()));
    }

    @Override
    public Value<?> visit(ASTAnd e, Env<Value<?>> env) throws InvalidTypeException {
        BoolValue b1 = (BoolValue) e.left.accept(this, env);
        BoolValue b2 = (BoolValue) e.right.accept(this, env);
        return (new BoolValue(b1.getValue() && b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTOr e, Env<Value<?>> env) throws InvalidTypeException {
        BoolValue b1 = (BoolValue) e.left.accept(this, env);
        BoolValue b2 = (BoolValue) e.right.accept(this, env);
        return (new BoolValue(b1.getValue() || b2.getValue()));
    }

    //TODO: pode ser inteiro e booleano
    @Override
    public Value<?> visit(ASTDiff e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue b1 = (IntValue) e.arg1.accept(this, env);
        IntValue b2 = (IntValue) e.arg2.accept(this, env);
        return (new BoolValue(b1.getValue() != b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTLeq e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue b1 = (IntValue) e.arg1.accept(this, env);
        IntValue b2 = (IntValue) e.arg2.accept(this, env);
        return (new BoolValue(b1.getValue() <= b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTLt e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue b1 = (IntValue) e.arg1.accept(this, env);
        IntValue b2 = (IntValue) e.arg2.accept(this, env);
        return (new BoolValue(b1.getValue() < b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTGeq e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue b1 = (IntValue) e.arg1.accept(this, env);
        IntValue b2 = (IntValue) e.arg2.accept(this, env);
        return (new BoolValue(b1.getValue() >= b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTGt e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue b1 = (IntValue) e.arg1.accept(this, env);
        IntValue b2 = (IntValue) e.arg2.accept(this, env);
        return (new BoolValue(b1.getValue() > b2.getValue()));
    }

    //TODO: pode ser inteiro e booleano
    @Override
    public Value<?> visit(ASTEq e, Env<Value<?>> env) throws InvalidTypeException {
        IntValue b1 = (IntValue) e.arg1.accept(this, env);
        IntValue b2 = (IntValue) e.arg2.accept(this, env);
        return (new BoolValue(b1.getValue() == b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTRef e, Env<Value<?>> env) throws InvalidTypeException {
        return e.val.accept(this, env);
    }

    @Override
    public Value<?> visit(ASTNot astNot, Env<Value<?>> env) throws InvalidTypeException {
        return null;
    }


    public static Value<?> interpret(ASTNode e) throws InvalidTypeException {
        Interpreter i = new Interpreter();
        //TODO: check if this is correct because of env
        return e.accept(i, null);

    }
}
