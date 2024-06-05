package target.operations.relational;

public class LabelGenerator {
    private static long count = 0L;

    public static String genLabel() {
        return "L" + count++;
    }
}
