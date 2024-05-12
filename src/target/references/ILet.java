package target.references;

import ast.ASTNode;
import ast.value.ASTBool;
import symbols.Tuple;
import target.Instruction;

import java.util.Iterator;
import java.util.List;

public class ILet extends Instruction {
    public ILet(List<Tuple<String, ASTNode>> vars) {
        Iterator<Tuple<String, ASTNode>> it = vars.iterator();
        String variables = "";
        int varnum = 0;
        while(it.hasNext()){
            Tuple<String, ASTNode> var = it.next();
            String type = "";
            switch (var.item2().getClass().getName()){
                case "ASTBool":
                    type = "Z";
            variables += ".field public loc_" + varnum + ""
        }
        op = ".class frame_\n" +
                ".super java/lang/Object\n" +
                ".field public SL Lprev_frame_;\n" +
                variables +


    }
}
