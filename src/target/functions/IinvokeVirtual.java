package target.functions;

import target.Instruction;

public class IinvokeVirtual extends Instruction {
    public IinvokeVirtual(String... method) {
        op = "invokevirtual";
        args = method;
    }
}
