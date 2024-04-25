package value;

public class BoolValue implements Value<Boolean> {
	private boolean value;

public BoolValue(boolean value) {
		this.value = value;
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public String toString() {
		return Boolean.toString(value);
	}


	@Override
	public boolean equals(Object obj) {
		return obj instanceof BoolValue && value == ((BoolValue)obj).getValue();
	}


	
	
	
}
