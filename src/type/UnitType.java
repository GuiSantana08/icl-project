package type;

public class UnitType implements Type{
    public static final UnitType singleton = new UnitType();

    private static final String TYPE = "unit";
    private static final String JVM_TYPE = "Ljava/lang/Object;";

    private UnitType() {}

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String jvmType() {
        return JVM_TYPE;
    }
}
