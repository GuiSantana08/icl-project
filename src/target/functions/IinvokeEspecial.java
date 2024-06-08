package target.functions;

import target.Instruction;

public class IinvokeEspecial extends Instruction {
    public IinvokeEspecial(String... method) {
        op = "invokespecial";
        args = method;
    }
}
