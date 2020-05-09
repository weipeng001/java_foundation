package sets;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

/**

 * @Date: 2018/6/28 12:19
 * @Description:
 */
public class SetsTest {
    @Test
    public void cartesianProductTest() {
        List<Set<Integer>> setList = Lists.newArrayList();
        setList.add(Sets.newHashSet(1, 2));
        setList.add(Sets.newHashSet(3, 6, 7));
        Set<List<Integer>> result = Sets.cartesianProduct(setList);
        System.out.println(result);
        assertEquals(6, result.size());
    }

    @Test
    public void cartesianProductTest1() {
        Set<Integer> set1 = Sets.newHashSet(1, 2);
        Set<Integer> set2 = Sets.newHashSet(3, 6, 7);
        Set<List<Integer>> result = Sets.cartesianProduct(set1, set2);
        System.out.println(result);
        assertEquals(6, result.size());
    }

    @Test
    public void combinationsTest() {
        /*求组合结果 n! / r! * (n - r)!*/
        Set<Integer> set = Sets.newHashSet(1, 2, 3, 4, 5);
        Set<Set<Integer>> result = Sets.combinations(set, 2);
        result.forEach(System.out::println);
        assertEquals(10, result.size());
    }

    @Test
    public void differenceTest() {
        /*求差集，返回结果Set是一个视图，不支持插入、删除操作*/
        Set<Character> first = Sets.newHashSet('a', 'b', 'c');
        Set<Character> second = Sets.newHashSet('b', 'c', 'd');
        Set<Character> result = Sets.difference(first, second);
        System.out.println(result);
        result = Sets.difference(second, first);
        System.out.println(result);
    }

    @Test
    public void filterTest() {
        Set<Integer> set0 = Sets.newHashSet(1, 2, 3, 4, 5, 6);
        Set<Integer> filterResult0 = Sets.filter(set0, ele -> ele % 2 == 0);
        assertThat(filterResult0, containsInAnyOrder(2, 4, 6));

        Set<Integer> set1 = Sets.newLinkedHashSet(Lists.newArrayList(1, 2, 3, 4, 5, 6));
        Set<Integer> filterResult1 = Sets.filter(set1, ele -> ele % 2 == 1);
        assertThat(filterResult1, contains(1, 3, 5));

        Set<Integer> set2 = Sets.newTreeSet(Lists.newArrayList(1, 2, 3, 4, 5, 6));
        Set<Integer> filterResult2 = Sets.filter(set2, ele -> ele % 2 == 1);
        assertThat(filterResult2, contains(1, 3, 5));

    }

    @Test
    public void intersectionTest() {
        /*求交集，返回结果Set是一个视图，不支持插入、删除操作*/
        Set<Character> first = ImmutableSet.of('a', 'b', 'c');
        Set<Character> second = ImmutableSet.of('b', 'c', 'd');

        Set<Character> intersection = Sets.intersection(first, second);
        assertThat(intersection, containsInAnyOrder('b', 'c'));
    }

    @Test
    public void newConcurrentHashSetTest() {
        Set<Integer> set = Sets.newConcurrentHashSet();
        assertTrue(set.isEmpty());

        set = Sets.newConcurrentHashSet(Lists.newArrayList(1, 2, 3));
        assertThat(set, containsInAnyOrder(1, 2, 3));
    }

    @Test
    public void newCopyOnWriteArraySetTest() {
        Set<Integer> set = Sets.newCopyOnWriteArraySet();
        assertTrue(set.isEmpty());

        set = Sets.newCopyOnWriteArraySet(Lists.newArrayList(1, 2, 3));
        assertThat(set, containsInAnyOrder(1, 2, 3));
    }

    @Test
    public void newHashSetTest() {
        /*HashSet构造方法*/
        Set<Integer> set = Sets.newHashSet();
        assertTrue(set.isEmpty());

        set = Sets.newHashSet(1, 2, 3);
        assertThat(set, containsInAnyOrder(1, 2, 3));

        set = Sets.newHashSet(Lists.newArrayList(4, 5, 6));
        assertThat(set, containsInAnyOrder(4, 5, 6));

        set = Sets.newHashSet(Lists.newArrayList(0, 9, 8).iterator());
        assertThat(set, containsInAnyOrder(0, 9, 8));
    }

    @Test
    public void newHashSetWithExpectedSizeTest() {
        /*Set底层依赖于Map实现，newHashSetWithExpectedSize传入的数值为Map底层bucket数组大小，JDK实现为2的指数幂，所以下面代码底层Map的bucket数组实际大小为16*/
        Set<Integer> set = Sets.newHashSetWithExpectedSize(14);
        assertTrue(set.isEmpty());
    }

    @Test
    public void newLinkedHashSetTest() {
        /*HashSet构造方法*/
        Set<Integer> set = Sets.newLinkedHashSet();
        assertTrue(set.isEmpty());

        set = Sets.newLinkedHashSet(Lists.newArrayList(1, 2, 3));
        assertThat(set, containsInAnyOrder(1, 2, 3));

    }

    @Test
    public void newLinkedHashSetWithExpectedSizeTest() {
        /*Set底层依赖于Map实现，newLinkedHashSetWithExpectedSize传入的数值为Map底层bucket数组大小，JDK实现为2的指数幂，所以下面代码底层Map的bucket数组实际大小为16*/
        Set<Integer> set = Sets.newLinkedHashSetWithExpectedSize(14);
        assertTrue(set.isEmpty());
    }

    @Test
    public void newTreeSetTest() {
        Set<Integer> set = Sets.newTreeSet();
        assertTrue(set.isEmpty());

        set = Sets.newTreeSet(Lists.newArrayList(1, 2, 3));
        assertThat(set, containsInAnyOrder(1, 2, 3));
    }

    @Test
    public void unionTest() {
        /*求并集*/
        Set<Character> first = ImmutableSet.of('a', 'b', 'c');
        Set<Character> second = ImmutableSet.of('b', 'c', 'd');

        Set<Character> intersection = Sets.union(first, second);
        assertThat(intersection, containsInAnyOrder('a', 'b', 'c', 'd'));
    }
}
