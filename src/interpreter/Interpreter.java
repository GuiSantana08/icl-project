package interpreter;

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
import value.*;
//import value.RefValue;

import java.util.Objects;

public class Interpreter implements Visitor<Value<?>, Env<Value<?>>>{

    @Override
    public Value<?> visit(ASTInt i, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return new IntValue(i.value);
    }

    @Override
    public Value<?> visit(ASTBool b, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return new BoolValue(b.value);
    }

    @Override
    public Value<?> visit(ASTString s, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        return new StringValue(s.string);
    }

    @Override
    public Value<?> visit(ASTNeg e, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        IntValue n1 = (IntValue) e.arg.accept(this, env);
        return (new IntValue(-n1.getValue()));
    }

    @Override
    public Value<?> visit(ASTDiv e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        IntValue n1 = (IntValue) e.arg1.accept(this, env);
        IntValue n2 = (IntValue) e.arg2.accept(this, env);
        return (new IntValue(n1.getValue() / n2.getValue()));
    }

    @Override
    public Value<?> visit(ASTMult e, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        IntValue n1 = (IntValue) e.arg1.accept(this, env);
        IntValue n2 = (IntValue) e.arg2.accept(this, env);
        return (new IntValue(n1.getValue() * n2.getValue()));
    }

    @Override
    public Value<?> visit(ASTSub e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        IntValue n1 = (IntValue) e.arg1.accept(this, env);
        IntValue n2 = (IntValue) e.arg2.accept(this, env);
        return (new IntValue(n1.getValue() - n2.getValue()));
    }

    @Override
    public Value<?> visit(ASTAdd e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        IntValue n1 = (IntValue) e.arg1.accept(this, env);
        IntValue n2 = (IntValue) e.arg2.accept(this, env);
        return (new IntValue(n1.getValue() + n2.getValue()));
    }

    @Override
    public Value<?> visit(ASTAnd e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        BoolValue b1 = (BoolValue) e.left.accept(this, env);
        BoolValue b2 = (BoolValue) e.right.accept(this, env);
        return (new BoolValue(b1.getValue() && b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTOr e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        BoolValue b1 = (BoolValue) e.left.accept(this, env);
        BoolValue b2 = (BoolValue) e.right.accept(this, env);
        return (new BoolValue(b1.getValue() || b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTDiff e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        Value<?> arg1Value = e.arg1.accept(this, env);
        Value<?> arg2Value = e.arg2.accept(this, env);

        if (arg1Value instanceof IntValue intValue1 && arg2Value instanceof IntValue intValue2) {
            return new BoolValue(!Objects.equals(intValue1.getValue(), intValue2.getValue()));
        } else if (arg1Value instanceof BoolValue boolValue1 && arg2Value instanceof BoolValue boolValue2) {
            return new BoolValue(boolValue1.getValue() != boolValue2.getValue());
        } else {
            throw new InvalidTypeException("Arguments must be either both integers or both booleans.");
        }
    }

    @Override
    public Value<?> visit(ASTLeq e, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        IntValue b1 = (IntValue) e.arg1.accept(this, env);
        IntValue b2 = (IntValue) e.arg2.accept(this, env);
        return (new BoolValue(b1.getValue() <= b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTLt e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        IntValue b1 = (IntValue) e.arg1.accept(this, env);
        IntValue b2 = (IntValue) e.arg2.accept(this, env);
        return (new BoolValue(b1.getValue() < b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTGeq e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        IntValue b1 = (IntValue) e.arg1.accept(this, env);
        IntValue b2 = (IntValue) e.arg2.accept(this, env);
        return (new BoolValue(b1.getValue() >= b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTGt e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        IntValue b1 = (IntValue) e.arg1.accept(this, env);
        IntValue b2 = (IntValue) e.arg2.accept(this, env);
        return (new BoolValue(b1.getValue() > b2.getValue()));
    }

    @Override
    public Value<?> visit(ASTEq e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        Value<?> arg1Value = e.arg1.accept(this, env);
        Value<?> arg2Value = e.arg2.accept(this, env);

        if (arg1Value instanceof IntValue intValue1 && arg2Value instanceof IntValue intValue2) {
            return new BoolValue(Objects.equals(intValue1.getValue(), intValue2.getValue()));
        } else if (arg1Value instanceof BoolValue boolValue1 && arg2Value instanceof BoolValue boolValue2) {
            return new BoolValue(boolValue1.getValue() == boolValue2.getValue());
        } else {
            throw new InvalidTypeException("For equals the arguments must be either both integers or both booleans.");
        }
    }

    @Override
    public Value<?> visit(ASTNot astNot, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        BoolValue b = (BoolValue) astNot.arg.accept(this, env);
        return new BoolValue(!b.getValue());
    }

    @Override
    public Value<?> visit(ASTIfThenElse e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        BoolValue b = (BoolValue) e.condition.accept(this, env);
        if (b.getValue()) {
            return e.thenBranch.accept(this, env);
        } else {
            return e.elseBranch.accept(this, env);
        }
    }

    @Override
    public Value<?> visit(ASTWhile e, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        BoolValue b = (BoolValue) e.condition.accept(this, env);
        Value<?> result = null;
        while (b.getValue()) {
            result = e.body.accept(this, env);
            b = (BoolValue) e.condition.accept(this, env);
        }
        return result;
    }

    @Override
    public Value<?> visit(ASTSeq e, Env<Value<?>> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Value<?> visit(ASTDRef e, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        RefValue value = (RefValue) e.exp.accept(this, env);
        return value.getValue();
    }

    @Override
    public Value<?> visit(ASTPrint e, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Value arg = e.exp.accept(this, env);
        System.out.print(arg);
        return null;
    }

    @Override
    public Value<?> visit(ASTPrintln e, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Value arg = e.exp.accept(this, env);
        System.out.println(arg);
        return null;
    }

    @Override
    public Value<?> visit(ASTNew e, Env<Value<?>> env) throws InvalidTypeException , DuplicateVariableFoundException{
        Value<?> arg = e.exp.accept(this, env);
        return new RefValue(arg);
    }

    @Override
    public Value<?> visit (ASTLet e, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        env = env.beginScope();
        for(Tuple<String, ASTNode> t : e.vars) {
            Value<?> var = t.item2().accept(this, env);
            env.bind(t.item1(), var);
        }
        return e.body.accept(this, env);
    }

    @Override
    public Value<?> visit(ASTId e, Env<Value<?>> env) throws InvalidTypeException {
        return env.find(e.id);
    }

    @Override
    public Value<?> visit (ASTReff e, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        RefValue value = (RefValue) e.id.accept(this, env);
        value.setValue(e.exp.accept(this, env));
        System.out.println(value.getValue());
        return value;
    }


    public static Value<?> interpret(ASTNode e, Env<Value<?>> env) throws InvalidTypeException, DuplicateVariableFoundException {
        Interpreter i = new Interpreter();
        //TODO: check if this is corerect because of env
        return e.accept(i, env);

    }
}
