package lombok.test;

import com.sun.istack.internal.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.ExtensionMethod;
import lombok.extern.java.Log;

import java.util.Set;

/**
 * @Author wuweipeng
 * @Date 2021/1/16 23:58
 * @Description
 **/
@Builder//使用builder模式创建对象
@ToString//创建一个toString方法
//@Getter
//@Setter
@Data//所有属性都添加get、set方法
@NoArgsConstructor//创建一个无参构造函数
@AllArgsConstructor//创建一个全参构造函数
@Log//创建一个log属性
public class BuilderExample {
    /*如果created没有被build赋值，则取默认值*/
    @Builder.Default
    private long created = System.currentTimeMillis();

    private String name;

    private int age;

    /*表明成员变量是collection*/
    @Singular
    private Set<String> occupations;

    @ToString(includeFieldNames=false)
    @Data(staticConstructor="of")
    public static class Exercise<T> {
        private final String name;
        private final T value;
    }

    public int notNullTest(@NonNull String name) {
        return age;
    }

    /**
     * 生成对应的信息如下
     * public int synchronizedTest(String name) {
     *         synchronized(this.$lock) {
     *             return this.age;
     *         }
     *     }
     */
    @Synchronized
    public int synchronizedTest( String name) {
        return age;
    }


    public void logTest( ) {
        log.info("test");
    }




}
