package lombok.test;

import lombok.AccessLevel;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;


@Value
public class ValueExample {
    private String name;

    @Wither(AccessLevel.PACKAGE)
    @NonFinal
    private int age;

    private double score;
    protected String[] tags;

    @ToString
    @Value(staticConstructor="of")
    public static class Exercise<T> {
        String name;
        T value;
    }
}
