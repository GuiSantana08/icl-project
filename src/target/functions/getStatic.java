package target.functions;

import target.Instruction;

public class getStatic extends Instruction {
    public getStatic(String... field) {
        op = "getstatic ";
        args = field;
    }
}
