package target.relational;

import target.Instruction;

public class If_ICmpDiff extends Instruction {
        public If_ICmpDiff(String label) {
            op = "if_icmpneq " + label;
            args = null;
        }
}
