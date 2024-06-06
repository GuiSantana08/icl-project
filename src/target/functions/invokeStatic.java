package target.functions;

import target.Instruction;

public class invokeStatic extends Instruction {
    public invokeStatic(String... method) {
        op = "invokestatic ";
        args = method;
    }

}
