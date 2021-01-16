package lombok.test;

import com.google.common.collect.Sets;
import lombok.experimental.Accessors;
import lombok.experimental.var;
import lombok.val;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;


/**
 * @Author wuweipeng
 * @Date 2021/1/16 23:31
 * @Description
 **/
public class LombokTest {

    /**
     * val: final 像动态语言一样，声明一个fianl的变量。
     */
    @Test
    public void valTest() {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        System.out.println(foo.toLowerCase());

      /*  翻译成java 版本                                                                                          去q·
        final ArrayList<String> example = new ArrayList<String>();
        example.add("Hello, World!");
        final String foo = example.get(0);
        foo.toLowerCase();*/
    }

    /**
     * var: 同JDK10
     * 需要存在lombok.config 配置lombok.var.flagUsage = ALLOW
     */
    @Test
    public void varTest() {
        //  var ->  List<String>
        var example = new ArrayList<String>();
        example.add("Hello, World!");
        //  var ->  String
        var foo = example.get(0);
        System.out.println(foo.toLowerCase());

      /*  翻译成java 版本                                                                                          去q·
        ArrayList<String> example = new ArrayList<String>();
        example.add("Hello, World!");
        String foo = example.get(0);
        foo.toLowerCase();*/
    }

    /**
     * 需要存在lombok.config 配置lombok.var.flagUsage = ALLOW
     */
    @Test
    public void builderTest() {
        BuilderExample builderExample = BuilderExample.builder().name("test").age(22).occupation("haha").build();
        System.out.println(builderExample);
        BuilderExample builderExample1 = BuilderExample.builder().occupations(Sets.newHashSet("this", "is", "builder")).build();
        System.out.println(builderExample1);
    }

    /**
     * @data 注解会自动生成get set方法
     */
    @Test
    public void dataTest() {
        BuilderExample builderExample = new BuilderExample();
        builderExample.setAge(12);
        System.out.println(builderExample.getAge());
    }

    /**
     * @NonNull：在参数中使用时，如果调用时传了null值，就会抛出空指针异常
     */
    @Test
    public void NonNullTest() {
        BuilderExample builderExample = new BuilderExample();
        int i = builderExample.notNullTest(null);
    }

    /**
     * @NonNull：在参数中使用时，如果调用时传了null值，就会抛出空指针异常
     */
    @Test
    public void SynchronizedTest() {
        BuilderExample builderExample = new BuilderExample();
        int i = builderExample.synchronizedTest(null);
    }

    @Test
    public void logTest() {
        BuilderExample builderExample = new BuilderExample();
        builderExample.logTest();
    }

    @Test
    public void noArgsConstructorTest() {
        BuilderExample builderExample = new BuilderExample();
        System.out.println(builderExample);
    }

    @Test
    public void AllArgsConstructorTest() {
        BuilderExample builderExample = new BuilderExample(1,"name",12,Sets.newHashSet("this", "is", "builder"));
        System.out.println(builderExample);
    }

    @Test
    public void ToStringTest() {
        BuilderExample builderExample = new BuilderExample(1,"name",12,Sets.newHashSet("this", "is", "builder"));
        System.out.println(builderExample.toString());
    }

    /**
     * @Accessors(prefix ={"a","b","p"}, chain = true)
     *     prefix代表生成get set时只生成 a、b、p 开头的属性并且 a、b、p后面的第一个字母必须是大写
     *     chain代表是否支持链式设置属性，默认是false，不支持链式设置
     *     AccessorsTest生成的getter和setter方法如下，方法体略
     *     public Long id() {}
     *     public User id(Long id) {}
     *     public String name() {}
     *     public User name(String name) {}
     *     ......
     *     可以自己看看target包下的AccessorsTest对应class字节码文件
     */
    @Test
    public void AccessorsTest() {
        //方式一
        //@Accessors(prefix ={"a","b","p"}, chain = true)
        //AccessorsTest accessorsTest = new AccessorsTest().setId(1L).setName("11").setTestB("B").setTestA("A");

        //方式二
        // @Accessors(prefix ={"a","b","p"}, fluent = true,chain = true)
        //fluent设置为true，区别在于getter和setter不带set和get前缀。
        AccessorsTest accessorsTest = new AccessorsTest().id(1L).name("11").testA("B").testB("A");
        System.out.println(accessorsTest);

    }

    /**
     * @RequiredArgsConstructor 注解默认会生成一个静态的构造方法
     * 如果属性上没加 @NonNull 则会生成一个空的静态构造方法
     */
    @Test
    public void RequiredArgsConstructorTest() {
        RequiredArgsConstructorTest test = RequiredArgsConstructorTest.test("name");
        System.out.println(test);
    }


    @Test
    public void UtilityClass() {
        System.out.println(UtilityClassTest.name());
    }
}
