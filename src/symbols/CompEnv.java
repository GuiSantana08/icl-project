package symbols;

import ast.ASTNode;
import type.Type;

import java.util.Hashtable;
import java.util.Map;

public class CompEnv {

    public Map<String, Tuple<Integer, Type>> table;

    public CompEnv prev;

    public CompEnv(CompEnv prev){
        table = new Hashtable<>(20);
        this.prev = prev;
    }

    private Tuple<Integer, Tuple<Integer, Type>> findIt(String id) {
        CompEnv env = this;
        Tuple<Integer, Type> tuple;
        int depth = -1;
        do{
            tuple = env.table.get(id);
            env = env.prev;
            depth++;
        }while (tuple == null && env != null);
        return new Tuple<>(depth, tuple);
    }

    public Tuple<Integer, Tuple<Integer, Type>> find(String id) {
        return findIt(id);
    }

    public void bind(String varId, Type t) {
        Tuple<Integer, Type> tuple = new Tuple<>(table.size(), t);
        table.putIfAbsent(varId, tuple);
    }

}
