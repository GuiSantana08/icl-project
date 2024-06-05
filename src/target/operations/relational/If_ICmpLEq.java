package target.operations.relational;

import target.Instruction;

public class If_ICmpLEq extends Instruction {
        public If_ICmpLEq(String label) {
            op = "if_icmple " + label;
            args = null;
        }
}
