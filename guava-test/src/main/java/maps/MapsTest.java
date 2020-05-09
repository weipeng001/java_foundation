package maps;

import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**

 * @Date: 2018/7/4 12:28
 * @Description:
 */
public class MapsTest {
    @Test
    public void asMapTest() {
        /*asSet可用来将Set、SortedSet、NavigableSet转Map*/
        Map<String, Integer> compareMap = Maps.newHashMap();
        compareMap.put("This", 4);
        compareMap.put("is", 2);
        compareMap.put("test", 4);

        Set<String> hashSet = Sets.newHashSet(Lists.newArrayList("This", "is", "test"));
        Map<String, Integer> map0 = Maps.asMap(hashSet, String::length);
        assertThat(map0, is(compareMap));

        Set<String> treeSet = Sets.newTreeSet("This", "is", "test");
        Map<String, Integer> map1 = Maps.asMap(treeSet, String::length);
        assertThat(map1, equalTo(map0));

        Set<String> sortedSet = Sets.newLinkedHashSet("This", "is", "test");
        Map<String, Integer> map2 = Maps.asMap(sortedSet, String::length);
        assertThat(map2, is(map1));

        /*通过java8 Stream 也可以实现*/
        Map<String, Integer> map3 = sortedSet.stream().collect(Collectors.toMap(ele -> ele, String::length));
        assertThat(map3, is(map2));
    }

    @Test
    public void differenceTest() {
        Map<String, Integer> left = Maps.newHashMap();
        Map<String, Integer> right = Maps.newHashMap();
        left.put("Michael", 18);
        left.put("Jane", 20);
        left.put("Mary", 22);
        left.put("haha", 22);

        right.put("Michael", 19);
        right.put("Jane", 18);
        right.put("Mary", 22);
        right.put("zhuoli", 23);

        /*left与right的差*/
        MapDifference<String, Integer> difference = Maps.difference(left, right);
        /*left - right {haha=22}*/
        Map<String, Integer> entriesOnlyOnLeft = difference.entriesOnlyOnLeft();
        System.out.println(entriesOnlyOnLeft);
        /*right - left {zhuoli=23}*/
        Map<String, Integer> entriesOnlyOnRight = difference.entriesOnlyOnRight();
        System.out.println(entriesOnlyOnRight);
        /*left与right相同的Entry {Mary=22}*/
        Map<String, Integer> entriesInCommon = difference.entriesInCommon();
        System.out.println(entriesInCommon);
    }

    @Test
    public void filterEntriesTest() {
        /*根据Entry过滤Map*/
        Map<String, Integer> compareMap = Maps.newHashMap();
        compareMap.put("Jane", 20);
        compareMap.put("Mary", 22);

        Map<String, Integer> left = Maps.newHashMap();
        left.put("Michael", 18);
        left.put("Jane", 20);
        left.put("Mary", 22);

        Map<String, Integer> resultMap = Maps.filterEntries(left, ele -> ele.getValue() > 18);
        assertThat(resultMap, is(compareMap));

        /*Java8 Stream filter也可以，比较起来好像Guava的Maps更方便一些*/
        Map<String, Integer> streamResult = left.entrySet().stream().filter(ele -> ele.getValue() > 18).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        assertThat(streamResult, is(resultMap));
    }

    @Test
    public void filterKeysTest() {
        /*根据key过滤Map*/
        Map<String, Integer> compareMap = Maps.newHashMap();
        compareMap.put("Michael", 18);

        Map<String, Integer> left = Maps.newHashMap();
        left.put("Michael", 18);
        left.put("Jane", 20);
        left.put("Mary", 22);

        Map<String, Integer> resultMap = Maps.filterKeys(left, ele -> ele.length() > 4);
        assertThat(resultMap, is(compareMap));

        /*Java8 Stream filter也可以，比较起来好像Guava的Maps更方便一些*/
        Map<String, Integer> streamResult = left.entrySet().stream().filter(ele -> ele.getKey().length() > 4).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        assertThat(streamResult, is(resultMap));
    }

    @Test
    public void filterValuesTest() {
        /*根据value过滤Map*/
        Map<String, Integer> compareMap = Maps.newHashMap();
        compareMap.put("Michael", 18);

        Map<String, Integer> left = Maps.newHashMap();
        left.put("Michael", 18);
        left.put("Jane", 20);
        left.put("Mary", 22);

        Map<String, Integer> resultMap = Maps.filterValues(left, ele -> ele < 20);
        assertThat(resultMap, is(compareMap));

        /*Java8 Stream filter也可以，比较起来好像Guava的Maps更方便一些*/
        Map<String, Integer> streamResult = left.entrySet().stream().filter(ele -> ele.getValue() < 20).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        assertThat(streamResult, is(resultMap));
    }

    @Test
    public void fromPropertiesTest() {
        /*通过Properties构造ImmutableMap*/
        Map<String, String> compareMap = Maps.newHashMap();
        compareMap.put("this", "1");
        compareMap.put("is", "2");
        compareMap.put("test", "3");

        Properties properties = new Properties();
        /*注意Properties的value必须也为String, 否则会报NPE，详见Properties的getProperty方法(getProperty获取value为null,
        ImmutableMap.Builder put操作中ImmutableMap.entryOf操作会checkEntryNotNull，报NPE)*/
        properties.put("this", "1");
        properties.put("is", "2");
        properties.put("test", "3");

        /*fromProperties生成的是ImmutableMap<String, String>*/
        Map<String, String> map = Maps.fromProperties(properties);
        assertThat(map, is(compareMap));
    }

    @Test
    public void newConcurrentMapTest() {
        Map<String, Integer> map = Maps.newConcurrentMap();
        assertTrue(map.isEmpty());
    }

    @Test
    public void newHashMapTest() {
        Map<String, Integer> map = Maps.newHashMap();
        assertTrue(map.isEmpty());

        Map<String, Integer> sortedMap = Maps.newLinkedHashMap();
        sortedMap.put("zhuoli", 11);
        /*HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map)*/
        Map<String, Integer> map1 = Maps.newHashMap(sortedMap);
        assertThat(map1, hasEntry("zhuoli", 11));
    }

    @Test
    public void newHashMapWithExpectedSizeTest() {
        /*传入的数值为Map底层bucket数组大小，JDK实现为2的指数幂，所以下面代码底层Map的bucket数组实际大小为16*/
        Map<String, Integer> map = Maps.newHashMapWithExpectedSize(14);
        assertTrue(map.isEmpty());
    }

    @Test
    public void newLinkedHashMapTest() {
        Map<String, Integer> map = Maps.newLinkedHashMap();
        assertTrue(map.isEmpty());

        Map<String, Integer> sortedMap = Maps.newLinkedHashMap();
        sortedMap.put("zhuoli", 11);
        /*LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map)*/
        Map<String, Integer> map1 = Maps.newLinkedHashMap(sortedMap);
        assertThat(map1, hasEntry("zhuoli", 11));
    }

    @Test
    public void newLinkedHashMapWithExpectedSizeTest() {
        /*传入的数值为Map底层bucket数组大小，JDK实现为2的指数幂，所以下面代码底层Map的bucket数组实际大小为16*/
        Map<String, Integer> map = Maps.newLinkedHashMapWithExpectedSize(14);
        assertTrue(map.isEmpty());
    }

    @Test
    public void newTreeMapTest() {
        Map<String, Integer> map = Maps.newTreeMap();
        assertTrue(map.isEmpty());

        TreeMap<String, Integer> sortedMap = Maps.newTreeMap();
        sortedMap.put("zhuoli", 11);

        Map<String, Integer> map1 = Maps.newTreeMap(sortedMap);
        assertThat(map1, hasEntry("zhuoli", 11));
    }

    @Test
    public void subMapTest() {
        Map<Integer, String> compareMap = Maps.newHashMap();
        compareMap.put(1, "chenhao");
        compareMap.put(2, "zhuoli");

        TreeMap<Integer, String> idNameMap = Maps.newTreeMap();
        idNameMap.put(1, "chenhao");
        idNameMap.put(2, "zhuoli");
        idNameMap.put(3, "xiaoxian");
        idNameMap.put(4, "haha");
        /*定义子Map的key的范围为(0, 3)*/
        Range<Integer> range = Range.open(0, 3);
        Map<Integer, String> result = Maps.subMap(idNameMap, range);
        assertThat(result, is(compareMap));
    }

    @Test
    public void toMapTest() {
        Map<String, String> compareMap = Maps.newHashMap();
        compareMap.put("this", "THIS");
        compareMap.put("is", "IS");
        compareMap.put("test", "TEST");

        List<String> list = Lists.newArrayList("this", "is", "test", "is");

        /*ImmutableMap<K, V> toMap(Iterable<K> keys, Function<? super K, V> valueFunction)
         * 第二个参数为用于获取map value的函数表达式*/
        /*Guava toMap如果Iterable集合存在重复的情况，不会抛异常，会丢弃重复值*/
        Map<String, String> map = Maps.toMap(list, String::toUpperCase);
        System.out.println(map);
        assertThat(map, equalTo(compareMap));

        /*Java 8 stream toMap操作*/
        /*使用Java8 stream toMap操作时，如果key存在重复的情况，会抛异常IllegalStateException*/
        assertThatThrownBy(() -> list.stream().collect(Collectors.toMap(ele -> ele, String::toUpperCase)))
                .isInstanceOf(IllegalStateException.class)
                .hasNoCause();
    }

    @Test
    public void transformEntriesTest() {
        /*根据Entry转化Map(根据key和value修改Map的value值)*/
        Map<String, String> compareMap = Maps.newHashMap();
        compareMap.put("this", "this4");
        compareMap.put("is", "is2");
        compareMap.put("test", "test4");

        Map<String, Integer> map = Maps.newHashMap();
        map.put("this", 4);
        map.put("is", 2);
        map.put("test", 4);

        Map<String, String> resultMap = Maps.transformEntries(map, (k, v) -> k + v.toString());
        assertThat(resultMap, equalTo(compareMap));

        Map<String, Integer> resultMap1 = Maps.transformEntries(map, (k, v) -> v + 1);
        assertThat(resultMap1, hasEntry("this", 5));

        /*Java 8 Stream Collectors.toMap转化Map, Guava使用简单一些，但是Java8适用性更强，可以同时转化key和value*/
        Map<String, String> resultMap2 = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, ele -> ele.getKey() + ele.getValue()));
        assertThat(resultMap2, equalTo(compareMap));

        Map<String, String> resultMap3 = map.entrySet().stream().collect(Collectors.toMap(ele -> ele.getKey().toUpperCase(), ele -> ele.getKey() + ele.getValue()));
        assertThat(resultMap3, hasEntry("THIS", "this4"));
    }

    @Test
    public void transformValuesTest(){
        /*通过Map的value转化Map(根据value修改value值)*/
        Map<String, Integer> compareMap = Maps.newHashMap();
        compareMap.put("this", 5);
        compareMap.put("is", 3);
        compareMap.put("test", 5);

        Map<String, Integer> map = Maps.newHashMap();
        map.put("this", 4);
        map.put("is", 2);
        map.put("test", 4);

        Map<String, Integer> resultMap = Maps.transformValues(map, value -> value + 1);
        assertThat(resultMap, is(compareMap));

        /*Java8 Stream 操作*/
        Map<String, Integer> resultMap1 = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, ele -> ele.getValue() + 1));
        assertThat(resultMap1, is(compareMap));
    }

    @Test
    public void uniqueIndexTest(){
        Map<Integer, String> compareMap = Maps.newHashMap();
        compareMap.put(4, "this");
        compareMap.put(2, "is");

        /*Iterable作为Map的value，通过函数表达式生成key组成map*/
        List<String> list = Lists.newArrayList("this", "is");

        Map<Integer, String> map = Maps.uniqueIndex(list, String::length);
        assertThat(map, is(compareMap));

        /*如果函数式表达式生成的key存在重复的情况，会抛IllegalArgumentException异常*/
        List<String> list1 = Lists.newArrayList("this", "is", "test");
        assertThatThrownBy(()->Maps.uniqueIndex(list1, String::length))
                .isInstanceOf(IllegalArgumentException.class)
                .hasNoCause();

        /*uniqueIndex Java8 Stream等价操作*/
        Map<Integer, String> map1 = list.stream().collect(Collectors.toMap(String::length, ele -> ele));
        assertThat(map1, is(compareMap));

        /*Java8 key重复会抛IllegalStateException异常*/
        assertThatThrownBy(()->list1.stream().collect(Collectors.toMap(String::length, ele -> ele)))
                .isInstanceOf(IllegalStateException.class)
                .hasNoCause();
    }
}
