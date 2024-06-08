package compiler.struct;

import java.util.ArrayList;
import java.util.List;

public class Closure {

    public int nFields;
    public List<String> types;
    public int id;
    public Closure prev;

    public Closure(int fields, int id, Closure prev) {
        this.nFields = fields;
        this.id = id;
        this.prev = prev;
        this.types = new ArrayList<>();
    }

}
