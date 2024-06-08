package symbols;

import ast.ASTNode;

import java.util.Hashtable;
import java.util.Map;

public class CompEnv {

    public Map<String, Integer> table;

    public CompEnv prev;

    public CompEnv(CompEnv prev){
        table = new Hashtable<>(20);
        this.prev = prev;
    }

    private Tuple<Integer, Integer> findIt(String id) {
        Integer loc;
        CompEnv env = this;
        int depth = -1;
        do{
            loc = env.table.get(id);
            env = env.prev;
            depth++;
        }while (loc == null && env != null);
        return new Tuple<>(depth, loc);
    }

    public Tuple<Integer, Integer> find(String id) {
        return findIt(id);
    }

    public void bind(String varId) {
        table.putIfAbsent(varId, table.size());
    }

}
