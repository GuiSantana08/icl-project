package type;

public class UnitType implements Type{
    public static final UnitType singleton = new UnitType();

    private static final String TYPE = "unit";
    private static final String JVM_TYPE = "Ljava/lang/Object;";

    private Type type;

    public UnitType() {}

    public UnitType(Type type) {
        this.type = type;
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
