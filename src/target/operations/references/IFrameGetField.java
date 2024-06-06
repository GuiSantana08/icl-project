package target.operations.references;

import target.Instruction;

public class IFrameGetField extends Instruction {

    public IFrameGetField(String field) {
        op = "getfield " + field;
        args = null;
    }

}
