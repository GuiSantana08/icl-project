package target.relational;

import compiler.struct.Frame;
import symbols.CompEnv;
import symbols.Tuple;
import target.BasicBlock;
import target.Instruction;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class BlockSeq {
    List<Frame> frames;
    Frame currFrame;

    public BasicBlock block;

    public CompEnv env;

    public BlockSeq(){
        this.frames = new ArrayList<>();
        this.block = new BasicBlock();
        this.env = new CompEnv();
    }

    public Tuple<Frame, CompEnv> beginScope(int nFields) {
        return new Tuple<>(new Frame(nFields), env);
    }

    public void advanceToFrame(Frame f, CompEnv env){
        this.currFrame = f;
        frames.add(f);
    }

    public void endScope(Frame f, CompEnv env){

    }

    public void fetch(String id, Type t) {
        env.find(id);
    }

    public void addInstruction(Instruction i){
        this.block.addInstruction(i);
    }

    public void build(StringBuilder sb){
        block.build(sb);
    }
}
