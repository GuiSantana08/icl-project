package target.relational;

import target.Instruction;

public class INot extends Instruction {
    public INot() {
        op = "ixor";
        args = null;
    }
}
