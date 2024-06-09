package type;

public class RefType implements Type{
    public static final RefType singleton = new RefType(null);

    private static final String TYPE = "ref_";
    private static final String JVM_TYPE = "Lref_";

    private Type type;

    public RefType(Type type) {
        this.type = type;
    }

    //TODO: correct this
    @Override
    public String getType() {
        return TYPE + type.getType();
    }

    @Override
    public String jvmType() {
        return JVM_TYPE + type.getType();
    }

    public Type getRefType(){
        return type;
    }


}
