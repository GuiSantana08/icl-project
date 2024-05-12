package target.references;

import target.Instruction;

public class IDRef extends Instruction {

    public IDRef() {
        op = "iconst_";
        args = null;
    }

}
