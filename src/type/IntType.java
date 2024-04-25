package type;

public class IntType implements Type{
    public static final IntType singleton = new IntType();

    private static final String TYPE = "int";
    private static final String JVM_TYPE = "I";

    public IntType() {

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
