package target.operations;

import target.Instruction;

public class New extends Instruction {

        public New(String type) {
            op = "new";
            args = new String[] {type};
        }
}
