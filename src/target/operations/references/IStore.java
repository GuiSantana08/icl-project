package target.operations.references;

import target.Instruction;

public class IStore extends Instruction {

    public IStore(String... args) {
        op = "astore";
        this.args = args;
    }

}
