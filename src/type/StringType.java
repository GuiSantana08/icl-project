package type;

public class StringType implements Type{
    public static final StringType singleton = new StringType();

    private static final String TYPE = "str";
    private static final String JVM_TYPE = "S"; //TODO: verify JVM type

    public StringType() {

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
