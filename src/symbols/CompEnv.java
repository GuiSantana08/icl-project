package symbols;

import java.util.Hashtable;
import java.util.Map;

public class CompEnv {

    private Map<Integer, Integer> table;

    private CompEnv prev = null;

    public CompEnv(){
        table = new Hashtable<>(20);
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

}
