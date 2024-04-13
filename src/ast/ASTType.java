package ast;

import type.Type;
import type.TypeVoid;

public abstract class ASTType implements ASTNode {
    private Type type;

    public ASTType() {
        this.type = new TypeVoid();
    }

    public ASTType(Type type){
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Type setType(Type type) {
        this.type = type;
        return this.getType();
    }





}
