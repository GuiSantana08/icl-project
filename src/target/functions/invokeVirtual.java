package target.functions;

import target.Instruction;

public class invokeVirtual extends Instruction {
    public invokeVirtual(String... method) {
        op = "invokevirtual ";
        args = method;
    }
}
