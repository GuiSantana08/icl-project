package compiler;

import ast.ASTNode;
import ast.operations.arithmetic.*;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import exceptions.InvalidTypeException;
import symbols.Env;
import target.BasicBlock;
import type.Type;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CodeGen implements ast.ASTNode.Visitor<Void, Env<Type>>{

    BasicBlock block = new BasicBlock();


    @Override
    public Void visit(ASTInt i, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTBool b, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTNeg e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTDiv e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTMult e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTSub e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTAdd e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTDiff e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTLeq e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTLt e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTGeq e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTGt e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTEq e, Env<Type> env) throws InvalidTypeException {
        return null;
    }

    public static BasicBlock codeGen(ASTNode e) throws InvalidTypeException {
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

    public static void writeToFile(ASTNode e, String filename) throws FileNotFoundException, InvalidTypeException {
        StringBuilder sb = genPreAndPost(codeGen(e));
        PrintStream out = new PrintStream(new FileOutputStream(filename));
        out.print(sb.toString());
        out.close();

    }
}
