package ast;

import type.Type;
import value.Value;

public abstract class ASTValue implements ASTNode {
    private Value type;

    public ASTValue() {
        this.type = null; // void type
    }

    public ASTValue(Value type){
        this.type = type;
    }

    public Value getType() {
        return type;
    }

    public Value setType(Value type) {
        this.type = type;
        return this.getType();
    }





}
