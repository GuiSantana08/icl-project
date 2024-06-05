package target.references;

import target.Instruction;

public class IJump extends Instruction {

    public IJump(String label) {
        op = "goto";
        args = new String[] {label};
    }

}
