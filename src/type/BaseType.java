package type;

public abstract class BaseType implements Type{
    private final String type;
    private final String jvmType;

    public BaseType(String type, String jvmType){
        this.type = type;
        this.jvmType = jvmType;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public String jvmType() {
        return jvmType;
    }

}
