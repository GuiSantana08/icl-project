package compiler;

import ast.ASTNode;
import ast.ASTNode.Visitor;
import ast.control.ASTIfThenElse;
import ast.control.ASTSeq;
import ast.control.ASTWhile;
import ast.functions.ASTDefFun;
import ast.functions.ASTFunCall;
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
import target.references.*;
import target.relational.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

public class CodeGen implements Visitor<Void, Void>{

    int frameId;

    BlockSeq block = new BlockSeq();


    @Override
    public Void visit(ASTInt i, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        block.addInstruction(new SIPush(i.value) );
        return i.accept(this, v);
    }

    @Override
    public Void visit(ASTBool b, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        block.addInstruction(new SIPush(b.value ? 1 : 0));
        return b.accept(this, v);
    }

    @Override
    public Void visit(ASTString e, Void v) throws InvalidTypeException {
        return null;
    }

    @Override
    public Void visit(ASTNeg e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg.accept(this, v);
        block.addInstruction(new INeg());
        return null;
    }

    @Override
    public Void visit(ASTDiv e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new IDiv());
        return null;
    }

    @Override
    public Void visit(ASTMult e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new IMul());
        return null;
    }

    @Override
    public Void visit(ASTSub e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new ISub());
        return null;
    }

    @Override
    public Void visit(ASTAdd e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new IAdd());
        return null;
    }

    @Override
    public Void visit(ASTAnd e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.left.accept(this, v);
        e.right.accept(this, v);
        block.addInstruction(new IAdd());
        return e.accept(this, v);
    }

    @Override
    public Void visit(ASTOr e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.left.accept(this, v);
        e.right.accept(this, v);
        block.addInstruction(new IOr());
        return e.accept(this, v);
    }

    @Override
    public Void visit(ASTDiff e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new If_ICmpDiff());
        return null;
    }

    @Override
    public Void visit(ASTLeq e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new If_ICmpLEq());
        return null;
    }

    @Override
    public Void visit(ASTLt e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new If_ICmpLt());
        return null;
    }

    @Override
    public Void visit(ASTGeq e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new If_ICmpGEq());
        return null;
    }

    @Override
    public Void visit(ASTGt e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new If_ICmpGt());
        return null;
    }

    @Override
    public Void visit(ASTEq e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new If_ICmpEq());
        return null;
    }


    @Override
    public Void visit(ASTNot astNot, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        astNot.arg.accept(this, v);
        block.addInstruction(new SIPush(0));
        block.addInstruction(new INot());
        return null;
    }

    @Override
    public Void visit(ASTId e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        e.accept(this, v);
        block.addInstruction(new IId());
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
        block.addInstruction(new IDRef());
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
    public Void visit(ASTDefFun e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        return null;
    }

    @Override
    public Void visit(ASTFunCall e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        return null;
    }

    @Override
    public Void visit(ASTLet e, Void v) throws InvalidTypeException, DuplicateVariableFoundException {
        Tuple<Frame, CompEnv> letDef = block.beginScope(e.vars.size(), frameId++, block.currFrame);
        block.addInstruction(new IFrameCreation(block.currFrame.id));

        int varsCount = 0;
        Iterator<Tuple<String, ASTNode>> it = e.vars.iterator();
        while (it.hasNext()){
            block.addInstruction(new ILoad());
            Tuple<String, ASTNode> var = it.next();
            letDef.item2().bind(var.item1());
            letDef.item1();
            var.item2().accept(this, v);
            block.addInstruction(new IFrameFieldCreation(block.currFrame.id, varsCount, var.item2().getType()));
        }
        block.addInstruction(new ILet(e.vars));
        e.body.accept(this, v);
        block.endScope(letDef.item1(), letDef.item2());
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
    public static BasicBlock codeGen(ASTNode e) throws InvalidTypeException, DuplicateVariableFoundException {
        CodeGen cg = new CodeGen();
        e.accept(cg, null);
        //e.accept(cg, null);
        return cg.block.block;
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
