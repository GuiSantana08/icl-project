package compiler.struct;

import symbols.CompEnv;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    public int nFields;
    public List<String> types;
    public int id;
    public Frame prev;

    public Frame(int fields, int id, Frame prev) {
        this.nFields = fields;
        this.id = id;
        this.prev = prev;
        this.types = new ArrayList<>();
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public void addType(String t) {
        this.types.add(t);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Frame getPrev() {
        return prev;
    }

    public void setPrev(Frame prev) {
        this.prev = prev;
    }
}
