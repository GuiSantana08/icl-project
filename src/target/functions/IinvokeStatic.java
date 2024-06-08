package target.functions;

import target.Instruction;

public class IinvokeStatic extends Instruction {
    public IinvokeStatic(String... method) {
        op = "invokestatic";
        args = method;
    }

}
