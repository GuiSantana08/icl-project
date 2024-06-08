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
import ast.functions.io.out.ASTExit;
import ast.operations.arithmetic.*;
import ast.operations.references.*;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import ast.value.ASTString;
import compiler.struct.Frame;
import symbols.CompEnv;
import symbols.Tuple;
import target.BlockSeq;
import target.Dup;
import target.Pop;
import target.SIPush;
import target.functions.*;
import target.operations.New;
import target.operations.arithmetic.*;
import target.operations.references.*;
import target.operations.relational.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CodeGen implements Visitor<Void, Void> {

    private static final String getBaseFrame = "Ljava/lang/Object;";
    private static final String putBaseFrame = "Ljava/lang/Object";
    int frameId;

    BlockSeq block = new BlockSeq();

    @Override
    public Void visit(ASTInt i, Void v) {
        block.addInstruction(new SIPush(i.value));
        return v;
    }

    @Override
    public Void visit(ASTBool b, Void v) {
        block.addInstruction(new SIPush(b.value ? 1 : 0));
        return v;
    }

    @Override
    public Void visit(ASTString e, Void v) {
        return v;
    }

    @Override
    public Void visit(ASTNeg e, Void v) {
        e.arg.accept(this, v);
        block.addInstruction(new INeg());
        return v;
    }

    @Override
    public Void visit(ASTDiv e, Void v) {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new IDiv());
        return v;
    }

    @Override
    public Void visit(ASTMult e, Void v) {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new IMul());
        return v;
    }

    @Override
    public Void visit(ASTSub e, Void v) {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new ISub());
        return v;
    }

    @Override
    public Void visit(ASTAdd e, Void v) {
        e.arg1.accept(this, v);
        e.arg2.accept(this, v);
        block.addInstruction(new IAdd());
        return v;
    }

    @Override
    public Void visit(ASTAnd e, Void v) {
        e.left.accept(this, v);
        e.right.accept(this, v);
        block.addInstruction(new IAdd());
        return v;
    }

    @Override
    public Void visit(ASTOr e, Void v) {
        e.left.accept(this, v);
        e.right.accept(this, v);
        block.addInstruction(new IOr());
        return v;
    }

    @Override
    public Void visit(ASTDiff e, Void v) {
        e.arg1.accept(this, v);
        String l1 = LabelGenerator.genLabel();
        e.arg2.accept(this, v);
        String l2 = LabelGenerator.genLabel();

        block.addInstruction(new If_ICmpDiff(l1));
        createLabels(l1, l2);
        return v;
    }

    @Override
    public Void visit(ASTLeq e, Void v) {
        e.arg1.accept(this, v);
        String l1 = LabelGenerator.genLabel();
        e.arg2.accept(this, v);
        String l2 = LabelGenerator.genLabel();

        block.addInstruction(new If_ICmpLEq(l1));
        createLabels(l1, l2);
        return null;
    }

    @Override
    public Void visit(ASTLt e, Void v) {
        e.arg1.accept(this, v);
        String l1 = LabelGenerator.genLabel();
        e.arg2.accept(this, v);
        String l2 = LabelGenerator.genLabel();

        block.addInstruction(new If_ICmpLt(l1));
        createLabels(l1, l2);
        return null;
    }

    @Override
    public Void visit(ASTGeq e, Void v) {
        e.arg1.accept(this, v);
        String l1 = LabelGenerator.genLabel();
        e.arg2.accept(this, v);
        String l2 = LabelGenerator.genLabel();

        block.addInstruction(new If_ICmpGEq(l1));
        createLabels(l1, l2);
        return null;
    }

    @Override
    public Void visit(ASTGt e, Void v) {
        e.arg1.accept(this, v);
        String l1 = LabelGenerator.genLabel();
        e.arg2.accept(this, v);
        String l2 = LabelGenerator.genLabel();

        block.addInstruction(new If_ICmpGt(l1));
        createLabels(l1, l2);
        return null;
    }

    @Override
    public Void visit(ASTEq e, Void v) {
        e.arg1.accept(this, v);
        String l1 = LabelGenerator.genLabel();
        e.arg2.accept(this, v);
        String l2 = LabelGenerator.genLabel();

        block.addInstruction(new If_ICmpEq(l1));
        createLabels(l1, l2);
        return null;
    }

    @Override
    public Void visit(ASTNot astNot, Void v) {
        astNot.arg.accept(this, v);
        block.addInstruction(new SIPush(0));
        block.addInstruction(new INot());
        return null;
    }

    @Override
    public Void visit(ASTId e, Void v) {
        Tuple<Integer, Integer> location = this.block.env.find(e.id);
        Frame actualFrame = block.currFrame;
        int depth = actualFrame.id;
        block.addInstruction(new ILoad());
        int targetDeep = location.item1();
        for(int i = 0; i < targetDeep; i++){
            String acFrame = "frame_" + actualFrame.id + "/sl";
            String prevFrame = "Lframe_" + (actualFrame.id - 1) + ";";
            block.addInstruction(new IgetField(acFrame, prevFrame));
            actualFrame = actualFrame.prev;
        }

//        while(depth >= targetDeep){
//            String acFrame = "frame_" + actualFrame.id + "/sl";
//            String prevFrame = "Lframe_" + (actualFrame.id - 1) + ";";
//            block.addInstruction(new IgetField(acFrame, prevFrame));
//            actualFrame = actualFrame.prev;
//            depth--;
//        }
        int targetPos = location.item2();
        String var = "frame_" + actualFrame.id + "/loc_" + targetPos;
        String varType = actualFrame.getTypes().get(location.item2());
        block.addInstruction(new IgetField(var, varType));


//        for (int i = 0; i < location.item1() - 1; i++) {
//            block.addInstruction(new ILoad());
//            String frameFile = "frame_" + actualFrame.id;
//            block.addInstruction(new IcheckCast(frameFile));
//            block.addInstruction(new IgetField("frame_" + actualFrame.id + "/SL Lframe_" + actualFrame.id + 1));
//           block.addInstruction(new IStore());
//        }
//        block.addInstruction(new IgetField("frame_" + actualFrame.id + "/loc_" + location.item2() + " " + actualFrame.getTypes().get(location.item2())));
       return null;
    }

    @Override
    public Void visit(ASTReff e, Void v) {
        e.exp.accept(this, v);
        Tuple<Integer, Integer> location = this.block.env.find( null/*TODO: WHY IS IT ASTNODE? - e.id*/);
        return null;
    }

    @Override
    public Void visit(ASTIfThenElse e, Void v) {
        e.condition.accept(this, v);
        String l1 = LabelGenerator.genLabel();
        String l2 = LabelGenerator.genLabel();
        block.addInstruction(new SIPush(0));
        block.addInstruction(new If_ICmpEq(l1));
        e.thenBranch.accept(this, v);
        block.addInstruction(new IJump(l2));
        block.addInstruction(new Label(l1));
        e.elseBranch.accept(this, v);
        block.addInstruction(new Label(l2));
        return null;
    }

    @Override
    public Void visit(ASTWhile e, Void v)  {
        String l1 = LabelGenerator.genLabel();
        block.addInstruction(new Label(l1));
        e.condition.accept(this, v);
        String l2 = LabelGenerator.genLabel();
        block.addInstruction(new SIPush(0));
        block.addInstruction(new If_ICmpEq(l2));
        e.body.accept(this, v);
        block.addInstruction(new Pop());
        block.addInstruction(new IJump(l1));
        block.addInstruction(new Label(l2));



        return null;
    }

    @Override
    public Void visit(ASTSeq e, Void v) {
        e.left.accept(this, v);
        block.addInstruction(new Pop());
        e.right.accept(this, v);
        return null;
    }

    @Override
    public Void visit(ASTDRef e, Void v) {
        e.exp.accept(this, v);
        block.addInstruction(new IDRef());
        return null;
    }

    @Override
    public Void visit(ASTPrint astPrint, Void v) {
        block.addInstruction(new IgetStatic("java/lang/System/out", "Ljava/io/PrintStream;"));
        astPrint.exp.accept(this, v);
        block.addInstruction(new IinvokeStatic("java/lang/String/valueOf(I)Ljava/lang/String;"));
        block.addInstruction(new IinvokeVirtual("java/io/PrintStream/print(Ljava/lang/String;)V"));
        return null;
    }

    @Override
    public Void visit(ASTPrintln astPrintln, Void v) {
        block.addInstruction(new IgetStatic("java/lang/System/out", "Ljava/io/PrintStream;"));
        astPrintln.exp.accept(this, v);
        block.addInstruction(new IinvokeStatic("java/lang/String/valueOf(I)Ljava/lang/String;"));
        block.addInstruction(new IinvokeVirtual("java/io/PrintStream/println(Ljava/lang/String;)V"));
        return null;
    }

    @Override
    public Void visit(ASTDefFun e, Void v) {
        return null;
    }

    @Override
    public Void visit(ASTFunCall e, Void v) {
        return null;
    }

    @Override
    public Void visit(ASTExit e, Void env) {
        return null;
    }

    @Override
    public Void visit(ASTLet e, Void v)  {
        Tuple<Frame, CompEnv> letDef = block.beginScope(e.vars.size(), frameId++, block.currFrame);
        createFrameCode(block.currFrame.id);
        Iterator<Tuple<String, ASTNode>> it = e.vars.iterator();
        while (it.hasNext()){
            CompEnv env = letDef.item2();
            Tuple<String, ASTNode> var = it.next();
            String id = var.item1();
            env.bind(id);
            block.addInstruction(new ILoad());
            ASTNode node = var.item2();
            node.accept(this, v);
            String frameFile = "frame_" + block.currFrame.id + "/loc_" + block.currFrame.getTypes().size();;
            String type = node.getJVMType();
            block.currFrame.addType(type);
            block.addInstruction(new IputField(frameFile, type));
        }
        genFrameCode(block.currFrame);
        e.body.accept(this, v);
        String frameFile = "frame_" + block.currFrame.id;
        EndFrameCode(block.currFrame.id);
        block.endScope(letDef.item1(), letDef.item2());
       return null;
    }

    private void EndFrameCode(int id) {
        if(id > 0){
            block.addInstruction(new ILoad());
            String acFrame = "frame_" + id + "/sl";
            String nextFrame = "Lframe_" + (id - 1) + ";";
            block.addInstruction(new IgetField(acFrame, nextFrame));
            block.addInstruction(new IStore("0"));
        } else {
            block.addInstruction(new ILoad());
            String acFrame = "frame_" + id + "/sl";
            block.addInstruction(new IgetField(acFrame, getBaseFrame));
            block.addInstruction(new IStore("0"));
        }
    }

    private void createFrameCode(int id) {
        block.addInstruction(new New("frame_" + id));
        block.addInstruction(new Dup());
        block.addInstruction(new IinvokeEspecial("frame_" + id + "/<init>()V"));
        block.addInstruction(new Dup());
        block.addInstruction(new ILoad());
        if (id == 0) {
            String frame1 = "frame_" + id +"/sl";
            block.addInstruction(new IputField(frame1, putBaseFrame));
        }
        else {
            String acFrame = "frame_" + id + "/sl";
            String nextFrame = "Lframe_" + (id - 1) + ";";
            block.addInstruction(new IputField(acFrame, nextFrame));
        }
        block.addInstruction(new IStore("0"));
    }

    @Override
    public Void visit(ASTNew e, Void v) {
        return null;
    }


    private void genFrameCode(Frame frame){
        String head;
        if (frame.id == 0)
            head = """
                    .class public frame_0
                    .super java/lang/Object
                    .field public sl Ljava/lang/Object;""";
        else
            head = """
                    .class public frame_%d
                    .super java/lang/Object
                    .field public sl Lframe_%d;""";
        StringBuilder variables = new StringBuilder();
        Iterator<String> vars = frame.types.iterator();
        int varCount = 0;
        while(vars.hasNext()){
            String t = vars.next();
            variables.append("\n.field public loc_").append(varCount).append(" ").append(t);
            varCount++;
        }
        String buttom = """
                \n.method public <init>()V
                aload_0
                invokenonvirtual java/lang/Object/<init>()V
                return
                .end method""";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(head, frame.id, frame.id-1));
        sb.append(variables);
        sb.append(buttom);
        String frameFile = "frame_" + frame.id + ".j";
        PrintStream file;
        try {
            Files.createDirectories(Paths.get("compOut"));
            file = new PrintStream(new FileOutputStream("compOut/" + frameFile));
            file.print(sb);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    private void generateFrameCode(Frame frame)  {
//        String code;
//        if(frame.id == 0)
//            code = """
//                   .class public frame_0
//                   .super java/lang/Object
//                   .field public sl Ljava/lang/Object;""";
//        else
//            code = """
//                    .class public frame_%d
//                    .super java/lang/Object
//                    .field public sl Lframe_%d;""";
//        Iterator<String> vars = frame.types.iterator();
//        int varCount = 0;
//        while(vars.hasNext()){
//            String t = vars.next();
//            code = code + "\n.field public loc_" + varCount + " " + t;
//        }
//        code = code + "\n.method public <init>()V\n" + new ILoad() + "\ninvokenonvirtual java/lang/Object/<init>()V\nreturn\n.end method";
//
//        StringBuilder sb = new StringBuilder();
//        if(frame.id == 0)
//            sb.append(code);
//        else
//            sb.append(String.format(code, frame.id, frame.id-1));
//        String frameFile = "frame_" + frame.id + ".j";
//        PrintStream file;
//        try {
//            Files.createDirectories(Paths.get("compOut"));
//            file = new PrintStream(new FileOutputStream("compOut/" + frameFile));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        file.print(sb);
//        file.close();
//    }

    public static BlockSeq codeGen(ASTNode e) {
        CodeGen cg = new CodeGen();
        e.accept(cg, null);
        return cg.block;
    }

    private void createLabels(String l1, String l2) {
        block.addInstruction(new SIPush(0));
        block.addInstruction(new IJump(l2));
        block.addInstruction(new Label(l1));
        block.addInstruction(new SIPush(1));
        block.addInstruction(new Label(l2));
    }

    private static StringBuilder genPreAndPost(BlockSeq block) {
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
				   """;
        String footer =
                """
                return
                .end method
                """;
        StringBuilder sb = new StringBuilder(preamble);
        block.build(sb);
        sb.append(footer);
        return sb;

    }

    public static void writeToFile(ASTNode e, String filename) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        BlockSeq block = new BlockSeq();
        block = codeGen(e);
        sb = genPreAndPost(block);
        PrintStream out = new PrintStream(new FileOutputStream("compOut/" + filename));
        out.print(sb.toString());
        out.close();

    }
}
