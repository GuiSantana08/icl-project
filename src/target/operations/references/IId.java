package target.operations.references;

import target.Instruction;

public class IId extends Instruction {

    public IId(int value) {
        op = "istore_" + value;
        args = null;
    }

}
