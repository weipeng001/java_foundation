package ints;

import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;


public class IntsTest {
    @Test
    public void asListTest() {
        /*基本类型数组转化为包装类List*/
        int[] intArray = {1, 2, 3, 4, 5};

        List<Integer> result = Ints.asList(intArray);
        assertThat(result, contains(1, 2, 3, 4, 5));
    }

    @Test
    public void checkedCastTest() {
        /*long转int，如果long值超出int范围抛IllegalArgumentException*/
        long input = 9998L;
        int result = Ints.checkedCast(input);
        assertEquals(9998, result);

        long input1 = 2147483648L;
        assertThatThrownBy(() -> Ints.checkedCast(input1)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Out of range: 2147483648").hasNoCause();
    }

    @Test
    public void compareTest() {
        /*比较两个int值的大小*/
        assertEquals(-1, Ints.compare(1, 2));
        assertEquals(0, Ints.compare(1, 1));
        assertEquals(1, Ints.compare(2, 1));
    }

    @Test
    public void concatTest() {
        /*将多个int数组拼接成一个数组*/
        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5, 6};
        int[] array3 = {7, 8};
        int[] result = Ints.concat(array1, array2, array3);
        assertEquals(8, result.length);
    }

    @Test
    public void constrainToRangeTest() {
        /*如果一个数字在某个范围内则输出该数字，否则输出范围的最大值或最小值*/
        int result = Ints.constrainToRange(5, 0, 9);
        assertEquals(5, result);
        assertEquals(9, Ints.constrainToRange(10, 0, 9));
        assertEquals(0, Ints.constrainToRange(-1, 0, 9));
    }

    @Test
    public void containsTest() {
        /*判断一个int数是否在int数组内*/
        int[] array = {1, 2, 3, 4};
        assertTrue(Ints.contains(array, 3));
    }

    @Test
    public void ensureCapacityTest() {
        /*确保数组拥有一个最小的长度*/
        int[] array = {1, 2, 3, 4};
        int[] result = Ints.ensureCapacity(array, 6, 3);
        assertEquals(9, result.length);

        int[] result1 = Ints.ensureCapacity(array, 3, 3);
        assertEquals(4, result1.length);
    }

    @Test
    public void fromByteArrayTest() {
        /*通过byte数组前四个元素转int值*/
        byte[] byteArray = {1, 1, 1, 1};
        int result = Ints.fromByteArray(byteArray);
        assertEquals(16843009, result);
    }

    @Test
    public void fromBytesTest() {
        /*通过四个byte元素转int值*/
        int result = Ints.fromBytes((byte) 1, (byte) 1, (byte) 1, (byte) 1);
        assertEquals(16843009, result);
    }

    @Test
    public void hashCodeTest() {
        /*返回int值的hashCode(元素值)*/
        int hashCode = Ints.hashCode(1);
        assertEquals(1, hashCode);
    }

    @Test
    public void indexOfTest() {
        /*返回一个int值在数组中的第一个index,没匹配到返回-1*/
        int[] array = {1, 2, 3, 4, 3};
        assertEquals(2, Ints.indexOf(array, 3));
        assertEquals(-1, Ints.indexOf(array, 5));
    }

    @Test
    public void arrayIndexOf() {
        /*返回int数组在另一个数组中的第一个index,没匹配到返回-1*/
        int[] array = {1, 2, 3, 4, 6, 5, 8};
        int[] target = {6, 5};
        assertEquals(4, Ints.indexOf(array, target));
    }

    @Test
    public void joinTest() {
        /*通过连接符连接数组转成String*/
        String str = "-";
        assertEquals("1-2-3", Ints.join(str, 1, 2, 3));
    }

    @Test
    public void lastIndexOfTest() {
        /*返回一个int值在数组中的最后一个index,没匹配到返回-1*/
        int[] array = {1, 2, 3, 4, 3};
        assertEquals(4, Ints.lastIndexOf(array, 3));
        assertEquals(-1, Ints.lastIndexOf(array, 5));
    }

    @Test
    public void lexicographicalComparatorTest() {
        /*返回一个int[]比较器，比较规则是从index0开始比较两个数组对应index上的元素大小，返回比较结果
         * 到其中一个数组结束都完全一致，则通过长度比较，长度大的那个数组大*/
        Comparator<int[]> comparator = Ints.lexicographicalComparator();
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 3, 3};
        int result = comparator.compare(array1, array2);
        assertEquals(-1, result);
    }

    @Test
    public void maxMinTest() {
        /*返回一个数组的最大元素*/
        int[] array = {1, 16, 3, 5, 3};
        int max = Ints.max(array);
        assertEquals(16, max);

        int min = Ints.min(array);
        assertEquals(1, min);
    }

    @Test
    public void reverseTest() {
        /*将数组反转*/
        int[] array = {1, 16, 3, 5, 3};
        Ints.reverse(array);
        List<Integer> reverseList = Ints.asList(array);
        assertThat(reverseList, contains(3, 5, 3, 16, 1));

        /*将数组制定范围的元素反转(范围左闭右开)*/
        int[] array1 = {1, 16, 8, 5, 3};
        Ints.reverse(array1, 0, 2);
        List<Integer> reverseList1 = Ints.asList(array1);
        System.out.println(reverseList1);
        assertThat(reverseList1, contains(16, 1, 8, 5, 3));
    }

    @Test
    public void saturatedCastTest() {
        /*将long转化为int,超出int范围转化为2147483647*/
        long input = 9998L;
        int result = Ints.saturatedCast(input);
        assertEquals(9998, result);

        long input1 = 2147483648L;
        int result1 = Ints.saturatedCast(input1);
        assertEquals(2147483647, result1);
    }

    @Test
    public void sortDescendingTest() {
        /*数组按逆序排序*/
        int[] array = {1, 16, 8, 5, 3};
        Ints.sortDescending(array);
        List<Integer> sortList = Ints.asList(array);
        assertThat(sortList, contains(16, 8, 5, 3, 1));

        int[] array1 = {1, 16, 8, 5, 3};
        /*将一定范围内的数组按照逆序排序(范围左闭右开)*/
        Ints.sortDescending(array1, 0, 3);
        List<Integer> sortList1 = Ints.asList(array1);
        assertThat(sortList1, contains(16, 8, 1, 5, 3));
    }

    @Test
    public void stringConverterTest() {
        /*返回String与Integer的转换器*/
        Converter<String, Integer> converter = Ints.stringConverter();
        int num = converter.convert("123");
        assertEquals(123, num);
    }

    @Test
    public void toArrayTest() {
        /*List转数组*/
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        int[] arr = Ints.toArray(list);
        assertEquals(1, arr[0]);
    }

    @Test
    public void toByteArrayTest() {
        /*int值转byte数组*/
        byte[] byteArray = Ints.toByteArray(1);
        assertEquals(1, byteArray[3]);
    }

    @Test
    public void tryParseTest(){
        /*十进制String转Integer, 如果String值存在非法字符,转为null*/
        Integer result = Ints.tryParse("1234");
        assertEquals(1234, result.intValue());
        assertEquals(null, Ints.tryParse("1234ahd"));

        /*radix进制String转Integer, 如果String值存在非法字符,转为null*/
        Integer result1 = Ints.tryParse("0110", 2);
        assertEquals(6, result1.intValue());
    }
}
