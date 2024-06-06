package compiler.struct;

import symbols.CompEnv;
import type.Type;

import java.util.List;

public class Frame {

    public int nFields;
    public List<Type> types;
    public int id;
    public Frame prev;

    public Frame(int fields, int id, Frame prev) {
        this.nFields = fields;
        this.id = id;
        this.prev = prev;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public void addType(Type t) {
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
