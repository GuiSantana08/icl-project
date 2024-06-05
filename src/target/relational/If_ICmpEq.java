package target.relational;

import target.Instruction;

public class If_ICmpEq extends Instruction {
        public If_ICmpEq(String label) {
            op = "if_icmpeq";
            args = new String[] {label};
        }
}
