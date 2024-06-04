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
    public Frame currFrame;

    public BasicBlock block;

    public CompEnv env;

    public BlockSeq(){
        this.frames = new ArrayList<>();
        this.block = new BasicBlock();
        this.env = new CompEnv(null);
    }

    public Tuple<Frame, CompEnv> beginScope(int nFields, int frameId, Frame prev) {
        Frame newFrame = new Frame(nFields, frameId, prev);
        frames.add(newFrame);
        this.currFrame = newFrame;

        this.env = new CompEnv(env);
        return new Tuple<>(newFrame, env);
    }

    public void advanceToFrame(Frame f, CompEnv env){
        this.currFrame = f;
        frames.add(f);
    }

    public void endScope(Frame f, CompEnv env){
        this.env = this.env.prev;

        this.currFrame = this.currFrame.getPrev();
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
