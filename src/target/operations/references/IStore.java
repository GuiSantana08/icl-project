package target.operations.references;

import target.Instruction;

public class IStore extends Instruction {

    public IStore() {
        op = "astore 0";
        args = null;
    }

}
