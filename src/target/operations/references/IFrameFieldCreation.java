package target.operations.references;

import target.Instruction;

public class IFrameFieldCreation extends Instruction {

    public IFrameFieldCreation(int id, int varCount, String type) {

        op = String.format("putfield frame_%d/loc_%d %s", id, varCount, type);
        args = null;

    }

}
