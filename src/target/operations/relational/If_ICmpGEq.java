package target.operations.relational;

import target.Instruction;

public class If_ICmpGEq extends Instruction {
        public If_ICmpGEq(String label) {
            op = "if_icmpge";
            args = new String[] {label};
        }
}
