package target.functions;

import target.Instruction;

public class IgetStatic extends Instruction {
    public IgetStatic(String... field) {
        op = "getstatic";
        args = field;
    }
}
