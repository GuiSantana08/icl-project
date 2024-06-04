package symbols;

import ast.ASTNode;

import java.util.Hashtable;
import java.util.Map;

public class CompEnv {

    public Map<String, Integer> table;

    public CompEnv prev = null;

    public CompEnv(CompEnv prev){
        table = new Hashtable<>(20);
        this.prev = prev;
    }

    private Tuple<Integer, Integer> findIt(String id) {
        Integer width;
        CompEnv env = this;
        int depth = -1;
        do{
            width = env.table.get(id);
            env = this.prev;
            depth++;
        }while (width == null && env != null);
        return new Tuple<>(depth, width);
    }

    public Tuple<Integer, Integer> find(String id) {
        return findIt(id);
    }

    public void bind(String varId) {
        table.putIfAbsent(varId, table.size());
    }

}
