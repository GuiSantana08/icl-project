package compiler.struct;

import symbols.CompEnv;
import type.Type;

import java.util.List;

public class Frame {

    private int nFields;
    private List<Type> types;
    private int id;
    private Frame prev;

    public Frame(int fields) {
        this.nFields = fields;
    }

    public String toString(){
        return null;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
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
