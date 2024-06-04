package target.references;

import target.Instruction;

public class IJump extends Instruction {

    public IJump(String label) {
        op = "jmp";
        args = new String[] {label};
    }

}
