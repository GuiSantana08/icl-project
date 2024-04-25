package type;

public class BoolType implements Type{
    private static final String JVM_TYPE = "Z";
    private static final String TYPE = "bool";

    public static final BoolType singleton = new BoolType();

    public BoolType() {
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String jvmType() {
        return JVM_TYPE;
    }
}
