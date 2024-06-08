package target.functions;

import target.Instruction;

public class IcheckCast  extends Instruction {
    public IcheckCast(String... type) {
        op = "checkcast";
        args = type;
    }
}
