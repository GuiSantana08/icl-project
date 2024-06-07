package target.operations.references;

import target.Instruction;

public class IFrameCreation extends Instruction {

    //TODO: change dup para apanhar a classe Dup
    private static final String frameCreationDef = """
            new frame_%s
            dup
            invokespecial frame_%s/<init>()V
            dup
            aload 0
            putfield frame_%s/SL %s
            astore 0""";

    public IFrameCreation(int frameId){
        if(frameId == 0) {
            op = String.format(frameCreationDef, frameId, frameId, frameId, "Ljava/lang/Object;");
        } else {
            op = String.format(frameCreationDef, frameId, frameId, frameId, "Lframe_" + (frameId - 1) + ";");
        }
        args = null;
    }
}
