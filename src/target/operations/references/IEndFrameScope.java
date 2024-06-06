package target.operations.references;

import target.Instruction;

public class IEndFrameScope extends Instruction {

    public IEndFrameScope(int frameId) {
        String load = new ILoad().op;
        String store = new IStore().op;
        if(frameId == 0)
            op =String.format(load + "\ngetfield frame_%s/sl " + "Ljava/lang/Object;\n" + store, frameId);
        else
            op =String.format(load + "\ngetfield frame_%s/sl " + "Lframe_%d;\n" + store, frameId, frameId-1);
        args = null;
    }

}
