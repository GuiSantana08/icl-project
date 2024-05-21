package value;

import ast.ASTNode;
import symbols.Env;
import symbols.Tuple;

import java.util.List;

public class ClosureValue implements Value{
    List<Tuple<String, String>> params;
    private ASTNode body;
    private Env<Value<?>> env;


    public ClosureValue(List<Tuple<String, String>> params, ASTNode body, Env<Value<?>> env) {
        this.params = params;
        this.body = body;
        this.env = env;
    }

    public List<String> getParams() {
        return params.stream().map(Tuple::item1).toList();
    }

    public String getParam(int i) {
        return params.get(i).item1();
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
