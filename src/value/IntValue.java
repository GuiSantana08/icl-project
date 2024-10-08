package value;

public class IntValue implements Value<Integer> {
	private int value;
	
	public IntValue(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof IntValue && value == ((IntValue)obj).getValue();
	}

	
}
