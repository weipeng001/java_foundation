package java8reduce;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**

 * @Date: 2018/7/18 20:31
 * @Description:
 */
public class ReduceTest {

    @Test
    public void reduceTest() {
        List<Integer> list = Lists.newArrayList();

        /*Optional accResult = Stream.of(1, 2, 3, 4)*/
        Optional accResult = list.stream()
                .reduce((acc, item) -> {
                    System.out.println("acc : " + acc);
                    acc += item;
                    System.out.println("item: " + item);
                    System.out.println("acc+ : " + acc);
                    System.out.println("--------");
                    return acc;
                });
        System.out.println(accResult.get());
    }

    @Test
    public void reduceTest1() {
        int accResult = Stream.of(1, 2, 3, 4)
                .reduce(100, (acc, item) -> {
                    System.out.println("acc : " + acc);
                    acc += item;
                    System.out.println("item: " + item);
                    System.out.println("acc+ : " + acc);
                    System.out.println("--------");
                    return acc;
                });
        System.out.println(accResult);
    }

    @Test
    public void reduceTest2() {
        ArrayList<Integer> accResult_ = Stream.of(2, 3, 4)
                .reduce(Lists.newArrayList(1),
                        (acc, item) -> {
                            acc.add(item);
                            System.out.println("item: " + item);
                            System.out.println("acc+ : " + acc);
                            System.out.println("BiFunction");
                            return acc;
                        }, (acc, item) -> {
                            System.out.println("BinaryOperator");
                            acc.addAll(item);
                            System.out.println("item: " + item);
                            System.out.println("acc+ : " + acc);
                            System.out.println("--------");
                            return acc;
                        }
                );
        System.out.println("accResult_: " + accResult_);
    }
}
