package value;

import ast.ASTNode;
import symbols.Env;

import java.util.List;

public class ClosureValue implements Value{
    List<String> params;
    private ASTNode body;
    private Env<Value<?>> env;


    public ClosureValue(List<String> params, ASTNode body, Env<Value<?>> env) {
        this.params = params;
        this.body = body;
        this.env = env;
    }

    public List<String> getParams() {
        return params;
    }

    public String getParam(int i) {
        return params.get(i);
    }

    public ASTNode getBody() {
        return body;
    }

    public Env<Value<?>> getEnv() {
        return env;
    }

    @Override
    public Object getValue() {
        return null;
    }
}
