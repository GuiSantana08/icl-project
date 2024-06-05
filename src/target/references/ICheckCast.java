package target.references;

import target.Instruction;

public class ICheckCast extends Instruction {

    public ICheckCast(int frameId) {
        op = "checkcast frame_" + frameId + "\n";
        args = null;
    }

}
