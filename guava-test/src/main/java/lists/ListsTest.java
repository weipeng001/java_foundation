package lists;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.apache.commons.lang.ArrayUtils;
import org.assertj.core.util.Sets;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**

 * @Date: 2018/6/27 10:14
 * @Description:
 */
public class ListsTest {

    @Test
    public void asListTest() {
        /*asList可用来将array转化为List,并在list头部插入值,不支持基本类型array*/
        int[] array = new int[]{1, 2, 3};

        List<Integer> list = Lists.asList(4, ArrayUtils.toObject(array));
        assertThat(list, contains(4, 1, 2, 3));

        /*list转array, 必须使用toArray(T[] array), 传入的是类型完全一样的数组，大小为list.size()
         * 如果使用无参toArray()方法，只能转成object[], 无法进行类型转换，强转会报ClassCastException*/
        Integer[] array2 = list.toArray(new Integer[list.size()]);
        list = Lists.asList(8, 9, array2);
        assertThat(list, contains(8, 9, 4, 1, 2, 3));
    }

    @Test
    public void cartesianProductTest() {
        /*cartesianProduct用来计算若干个List的笛卡尔乘积*/
        List<Integer> list1 = Lists.newArrayList(1, 2, 3);
        List<Integer> list2 = Lists.newArrayList(5, 6, 7, 8);
        List<Integer> list3 = Lists.newArrayList(0, 9);
        List<List<Integer>> cartesianResult = Lists.cartesianProduct(list1, list2, list3);
        System.out.println(cartesianResult);
        assertThat(cartesianResult, hasSize(24));

        /*嵌套的List也可以直接作为参数计算笛卡尔乘积*/
        List<List<Integer>> list = Lists.newArrayList(Lists.newArrayList(1, 2), Lists.newArrayList(5, 6));
        List<List<Integer>> cartesianResult1 = Lists.cartesianProduct(list);
        System.out.println(cartesianResult1);
    }

    @Test
    public void charactersOfTest() {
        /*charactersOf(String string)*/
        List<Character> characters = Lists.charactersOf("zhuoli");
        assertThat(characters, contains('z', 'h', 'u', 'o', 'l', 'i'));

        /*charactersOf(CharSequence sequence)*/
        characters = Lists.charactersOf(new StringBuilder("Michael"));
        assertThat(characters, contains('M', 'i', 'c', 'h', 'a', 'e', 'l'));
    }

    @Test
    public void newArrayListTest() {
        /*无参构造函数*/
        List<Integer> list = Lists.newArrayList();
        assertThat(list, empty());

        list = Lists.newArrayList(1, 2, 3);
        assertThat(list, contains(1, 2, 3));

        /*newArrayList(Iterable elements)*/
        list = Lists.newArrayList(Sets.newLinkedHashSet(1, 2, 4));
        assertThat(list, contains(1, 2, 4));
    }

    @Test
    public void newArrayListWithCapacityTest() throws NoSuchFieldException, IllegalAccessException {
        /*newArrayListWithCapacity直接指定返回的arrayList容量*/
        List<Integer> list0 = Lists.newArrayListWithCapacity(10);
        int capacity0 = getCapacity(list0);
        assertEquals(10, capacity0);

        /*newArrayListWithExpectedSize返回的arrayList容量为 5L + arraySize + (arraySize / 10)*/
        List<Integer> list1 = Lists.newArrayListWithExpectedSize(10);
        int capacity1 = getCapacity(list1);
        assertEquals(16, capacity1);
    }

    @Test
    public void newLinkedListTest() {
        List<Integer> list0 = Lists.newLinkedList();
        assertThat(list0, empty());

        /*newLinkedList(Iterable elements)*/
        List<Integer> list1 = Lists.newLinkedList(Sets.newLinkedHashSet(3, 4, 5));
        assertThat(list1, contains(3, 4, 5));
    }

    @Test
    public void CopyOnWriteArrayListTest() {
        List<Integer> list0 = Lists.newCopyOnWriteArrayList();
        assertThat(list0, empty());

        /*newCopyOnWriteArrayList(Iterable elements)*/
        List<Integer> list1 = Lists.newCopyOnWriteArrayList(Sets.newLinkedHashSet(3, 4, 5));
        assertThat(list1, contains(3, 4, 5));
    }

    @Test
    public void partitionTest() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        List<List<Integer>> partitionList = Lists.partition(list, 2);
        System.out.println(partitionList);
        assertEquals(4, partitionList.size());
    }

    @Test
    public void reverseTest() {
        List<String> names = Lists.newArrayList("John", "Adam", "Jane");

        List<String> reversed = Lists.reverse(names);
        assertThat(reversed, contains("Jane", "Adam", "John"));
    }

    @Test
    public void transformTest() {
        /*transform用来转化list, 建议直接使用Java8的Stream*/
        List<String> list = Lists.newArrayList("this", "is", "test");
        List<String> upperList = Lists.transform(list, new Function<String, String>() {
            @Override
            public String apply(@Nullable String s) {
                return s.toUpperCase();
            }
        });
        System.out.println(list);

        List<String> upperList1 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        assertEquals(upperList, upperList1);
    }

    @Test
    public void removeDuplicatesFromList() {
        /*去除list重复元素*/
        List<Character> chars = Lists.newArrayList('h', 'e', 'l', 'l', 'o');
        assertEquals(5, chars.size());
        List<Character> result = ImmutableSet.copyOf(chars).asList();
        assertThat(result, contains('h', 'e', 'l', 'o'));

        /*通过Java8 Stream去除重复元素, 建议使用*/
        chars = chars.stream().distinct().collect(Collectors.toList());
        assertEquals(result, chars);
    }

    /*通过反射获取list内部实现elementData的length属性*/
    private int getCapacity(List<Integer> list) throws NoSuchFieldException, IllegalAccessException {
        Field f = ArrayList.class.getDeclaredField("elementData");
        f.setAccessible(true);
        Object[] elementData = (Object[]) f.get(list);
        return elementData.length;
    }
}
