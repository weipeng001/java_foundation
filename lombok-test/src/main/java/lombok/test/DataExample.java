package lombok.test;

import lombok.*;


@Data
public class DataExample {
    private final String name;

    private final String name1 = "zhuoli";

    @NonNull
    private String name2;

    @NonNull
    private String name3 = "zhuoli";

    @Setter(AccessLevel.PACKAGE)
    private int age;

    private double score;

    private String[] tags;

    @ToString(includeFieldNames=false)
    @Data(staticConstructor="of")
    public static class Exercise<T> {
        private final String name;
        private final T value;
    }
}
