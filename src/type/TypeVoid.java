package type;

public class TypeVoid extends BaseType{
    private static final String TYPE = "void";
    private static final String JVM_TYPE = "V";

    public TypeVoid() {
        super(TYPE, JVM_TYPE);
    }
}
