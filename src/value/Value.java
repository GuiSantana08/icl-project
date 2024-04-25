package value;

public interface Value<T> {

    T getValue(); // Method to get the value

    String toString(); // Method to convert the value to a string representation

    boolean equals(Object obj); // Method to check equality with another object


}
