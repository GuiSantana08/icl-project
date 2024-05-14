package compiler;

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
import compiler.struct.Frame;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import symbols.CompEnv;
import symbols.Tuple;
import target.BasicBlock;
import target.SIPush;
import target.arithmetic.*;
import target.references.IDRef;
import target.references.IId;
import target.references.ILet;
import target.relational.*;
import type.Type;
import type.TypeChecker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public class CodeGen implements Visitor<Void, Void>{
    BlockSeq blocks;


    @Override
    public Void visit(ASTInt i, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        blocks.addInstruction(new SIPush(i.value) );
        return null;
    }

    @Override
    public Void visit(ASTBool b, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        blocks.addInstruction(new SIPush(b.value ? 1 : 0));
        return null;
    }

    @Override
    public Void visit(ASTString e, Void v) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTNeg e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg.accept(this, v);
        blocks.addInstruction(new INeg());
        return null;
    }

    @Override
    public Void visit(ASTDiv e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        blocks.addInstruction(new IDiv());
        return null;
    }

    @Override
    public Void visit(ASTMult e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        blocks.addInstruction(new IMul());
        return null;
    }

    @Override
    public Void visit(ASTSub e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        blocks.addInstruction(new ISub());
        return null;
    }

    @Override
    public Void visit(ASTAdd e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        blocks.addInstruction(new IAdd());
        return null;
    }

    @Override
    public Void visit(ASTAnd e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.left.accept(this, v);
        e.right.accept(this, v);
        blocks.addInstruction(new IAdd());
        return null;
    }

    @Override
    public Void visit(ASTOr e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.left.accept(this, v);
        e.right.accept(this, v);
        blocks.addInstruction(new IOr());
        return null;
    }

    @Override
    public Void visit(ASTDiff e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        blocks.addInstruction(new If_ICmpDiff());
        return null;
    }

    @Override
    public Void visit(ASTLeq e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        blocks.addInstruction(new If_ICmpLEq());
        return null;
    }

    @Override
    public Void visit(ASTLt e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        blocks.addInstruction(new If_ICmpLt());
        return null;
    }

    @Override
    public Void visit(ASTGeq e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        blocks.addInstruction(new If_ICmpGEq());
        return null;
    }

    @Override
    public Void visit(ASTGt e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        blocks.addInstruction(new If_ICmpGt());
        return null;
    }

    @Override
    public Void visit(ASTEq e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        blocks.addInstruction(new If_ICmpEq());
        return null;
    }


    @Override
    public Void visit(ASTNot astNot, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        astNot.arg.accept(this, v);
        blocks.addInstruction(new SIPush(1));
        blocks.addInstruction(new INot());
        return null;
    }

    @Override
    public Void visit(ASTId e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        blocks.fetch(e.id, e.type);
        return null;
    }

    @Override
    public Void visit(ASTReff e, Void v) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTWhile e, Void v) {
        return null;
    }

    @Override
    public Void visit(ASTSeq e, Void v) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTDRef e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.exp.accept(this, v);
        blocks.addInstruction(new IDRef());
        return null;
    }

    @Override
    public Void visit(ASTPrint astPrint, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        return null;
    }

    @Override
    public Void visit(ASTPrintln astPrintln, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        return null;
    }

    @Override
    public Void visit(ASTLet e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        int count = e.vars.size();
        Tuple<Frame, CompEnv> p = blocks.beginScope(count);
        Frame f = p.item1();
        CompEnv newEnv = p.item2();

        Iterator<Tuple<String, ASTNode>> it = e.vars.iterator();
        while (it.hasNext()){
            Tuple<String, ASTNode> var = it.next();
            f.getTypes().add(var.;
        }
        e.body.accept(this, null);
        blocks.endScope(f, newEnv);
        return null;
    }

    @Override
    public Void visit(ASTNew e, Void v) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTIfThenElse e, Void v) throws InvalidTypeException {
        return null;
    }
    public static BlockSeq codeGen(ASTNode e) throws InvalidTypeException, DuplicateVariableFoundException {
        CodeGen cg = new CodeGen();
        e.accept(cg, null);
        //e.accept(cg, null);
        return cg.blocks;
    }


    private static StringBuilder genPreAndPost(BlockSeq blocks) {
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
        blocks.build(sb);
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
