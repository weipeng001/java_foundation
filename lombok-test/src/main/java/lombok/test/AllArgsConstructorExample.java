package lombok.test;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AllArgsConstructorExample {
    private Integer x;

    private Integer y;

    private static Integer z;

    private final Integer z1;

    private final Integer z2 = 2;

    private static final Integer z3 = 1;
}
