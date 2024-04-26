package compiler;

import ast.ASTNode;
import ast.ASTNode.Visitor;
import ast.control.ASTIfThenElse;
import ast.control.ASTSeq;
import ast.control.ASTWhile;
import ast.operations.arithmetic.*;
import ast.operations.references.*;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import ast.value.ASTString;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import symbols.Env;
import target.BasicBlock;
import target.SIPush;
import target.arithmetic.*;
import target.relational.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CodeGen implements Visitor<Void, Env<Void>>{

    BasicBlock block = new BasicBlock();


    @Override
    public Void visit(ASTInt i, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        block.addInstruction(new SIPush(i.value) );
        return i.accept(this, env);
    }

    @Override
    public Void visit(ASTBool b, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        block.addInstruction(new SIPush(b.value ? 1 : 0));
        return b.accept(this, env);
    }

    @Override
    public Void visit(ASTString e, Env<Void> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTNeg e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg.accept(this, env);
        block.addInstruction(new INeg());
        return null;
    }

    @Override
    public Void visit(ASTDiv e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, env);
        e.arg2.accept(this, env);
        block.addInstruction(new IDiv());
        return null;
    }

    @Override
    public Void visit(ASTMult e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, env);
        e.arg2.accept(this, env);
        block.addInstruction(new IMul());
        return null;
    }

    @Override
    public Void visit(ASTSub e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, env);
        e.arg2.accept(this, env);
        block.addInstruction(new ISub());
        return null;
    }

    @Override
    public Void visit(ASTAdd e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, env);
        e.arg2.accept(this, env);
        block.addInstruction(new IAdd());
        return null;
    }

    @Override
    public Void visit(ASTAnd e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.left.accept(this, env);
        e.right.accept(this, env);
        //block.addInstruction(new SIPush());
        return e.accept(this, env);
    }

    @Override
    public Void visit(ASTOr e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.left.accept(this, env);
        e.right.accept(this, env);
        //block.addInstruction(new SIPush());
        return e.accept(this, env);
    }

    @Override
    public Void visit(ASTDiff e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, env);
        e.arg2.accept(this, env);
        block.addInstruction(new If_ICmpDiff());
        return null;
    }

    @Override
    public Void visit(ASTLeq e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, env);
        e.arg2.accept(this, env);
        block.addInstruction(new If_ICmpLEq());
        return null;
    }

    @Override
    public Void visit(ASTLt e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, env);
        e.arg2.accept(this, env);
        block.addInstruction(new If_ICmpLt());
        return null;
    }

    @Override
    public Void visit(ASTGeq e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, env);
        e.arg2.accept(this, env);
        block.addInstruction(new If_ICmpGEq());
        return null;
    }

    @Override
    public Void visit(ASTGt e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, env);
        e.arg2.accept(this, env);
        block.addInstruction(new If_ICmpGt());
        return null;
    }

    @Override
    public Void visit(ASTEq e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, env);
        e.arg2.accept(this, env);
        block.addInstruction(new If_ICmpEq());
        return null;
    }

    @Override
    public Void visit(ASTRef e, Env<Void> env) throws InvalidTypeException, DuplicateVariableFoundException {
        e.val.accept(this, env);
        //block.addInstruction(new SIPush());
        return null;
    }

    @Override
    public Void visit(ASTNot astNot, Env<Void> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTId e, Env<Void> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTAtrib e, Env<Void> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTWhile e, Env<Void> env) {
        return null;
    }

    @Override
    public Void visit(ASTSeq e, Env<Void> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTLet e, Env<Void> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTNew e, Env<Void> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTIfThenElse e, Env<Void> env) throws InvalidTypeException {
        return null;
    }
    public static BasicBlock codeGen(ASTNode e) throws InvalidTypeException, DuplicateVariableFoundException {
        CodeGen cg = new CodeGen();
        e.accept(cg, null);
        //e.accept(cg, null);
        return cg.block;
    }


    private static StringBuilder genPreAndPost(BasicBlock block) {
        String preamble = """
					  .class public Demo
					  .super java/lang/Object 
					  .method public <init>()V
					     aload_0
					     invokenonvirtual java/lang/Object/<init>()V
					     return
					  .end method
					  .method public static main([Ljava/lang/String;)V
					   .limit locals 10
					   .limit stack 256
					   ; setup local variables:
					   ;    1 - the PrintStream object held in java.lang.out
					  getstatic java/lang/System/out Ljava/io/PrintStream;					          
				   """;
        String footer =
                """
                invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
                invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
                return
                .end method
                """;
        StringBuilder sb = new StringBuilder(preamble);
        block.build(sb);
        sb.append(footer);
        return sb;

    }

    public static void writeToFile(ASTNode e, String filename) throws FileNotFoundException, InvalidTypeException, DuplicateVariableFoundException {
        StringBuilder sb = genPreAndPost(codeGen(e));
        PrintStream out = new PrintStream(new FileOutputStream(filename));
        out.print(sb.toString());
        out.close();

    }
}
