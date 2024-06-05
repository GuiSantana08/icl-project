package target.relational;

import target.Instruction;

public class If_ICmpGt extends Instruction {
        public If_ICmpGt(String label) {
            op = "if_icmpgt";
            args = new String[] {label};
        }
}
