package value;

public class StringValue implements Value{

    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringValue && value == ((StringValue)obj).getValue();
    }
}
