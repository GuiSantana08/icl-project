package target.operations.relational;

import target.Instruction;

public class Label extends Instruction {
    public Label(String label) {
        op = label + ":";
    }
}
