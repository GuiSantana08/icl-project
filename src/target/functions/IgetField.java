package target.functions;

import target.Instruction;

public class IgetField extends Instruction {
    public IgetField(String... field) {
        op = "getfield";
        args = field;
    }

}
