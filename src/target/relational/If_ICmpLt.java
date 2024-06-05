package target.relational;

import target.Instruction;

public class If_ICmpLt extends Instruction {
        public If_ICmpLt(String label) {
            op = "if_icmplt";
            args = new String[] {label};
        }
}
