package type;

import java.util.List;

public class ClosureType implements Type{
public static final ClosureType singleton = new ClosureType(null, null);

    private static final String TYPE = "closure_";
    private static final String JVM_TYPE = "Lclosure_";

    private Type returnType;
    private List<Type> params;

    public ClosureType(Type returnType, List<Type> params) {
        this.returnType = returnType;
        this.params = params;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String jvmType() {
        return returnType.jvmType();
    }

    public Type getReturnType(){
        return returnType;
    }

    public List<Type> getParams(){
        return params;
    }
}
