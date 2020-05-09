package lombok.test;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.Set;


@Builder
@ToString
@Getter
public class BuilderExample {
    /*如果created没有被build赋值，则取默认值*/
    @Builder.Default
    private long created = System.currentTimeMillis();

    private String name;

    private int age;

    /*表明成员变量是collection*/
    @Singular
    private Set<String> occupations;
}
