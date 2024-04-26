package type;

public class RefType implements Type{
    public static final RefType singleton = new RefType(null);

    private static final String TYPE = "ref";
    private static final String JVM_TYPE = "Ljava/lang/Object;";

    private Type type;

    public RefType(Type type) {
        this.type = type;
    }

    //TODO: correct this
    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String jvmType() {
        return JVM_TYPE;
    }

    public Type getRefType(){
        return type;
    }


}
