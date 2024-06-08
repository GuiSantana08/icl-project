package target.functions;

import target.Instruction;

public class IputField extends Instruction {
    public IputField(String... field) {
        op = "putfield";
        args = field;
    }
}
