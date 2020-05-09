package java8distinctbykey;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**

 * @Date: 2018/8/9 12:34
 * @Description:
 */
public class DistinctByKey {
    @Test
    public void distinctByKeyTest() {
        TestData testData1 = new TestData(1, 1, 100L);
        TestData testData2 = new TestData(1, 2, 1000L);
        TestData testData3 = new TestData(1, 3, 100L);
        TestData testData4 = new TestData(1, 1, 80L);

        TestData testData5 = new TestData(2, 1, 1600L);
        TestData testData6 = new TestData(2, 2, 1030L);
        TestData testData7 = new TestData(2, 2, 1001L);
        TestData testData8 = new TestData(2, 2, 1500L);

        TestData testData9 = new TestData(3, 5, 1500L);

        List<TestData> testDataList = Stream.of(testData1, testData2, testData3, testData4, testData5, testData6, testData7, testData8, testData9).collect(Collectors.toList());

        /*直接按照placement去重，scene为2的placement为1和2的元素被去掉*/
        List<TestData> distinctBykeyList = testDataList.stream().filter(distinctByKey(TestData::getPlacement)).collect(Collectors.toList());
        System.out.println(distinctBykeyList);

        Map<Integer, List<Integer>> resultMap = testDataList.stream().collect(Collectors.groupingBy(TestData::getScene)).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream().filter(distinctByKey(TestData::getPlacement)).map(TestData::getPlacement).collect(Collectors.toList())));
        System.out.println(resultMap);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
