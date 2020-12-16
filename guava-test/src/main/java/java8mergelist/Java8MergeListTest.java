package java8mergelist;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**

 * @Date: 2018/8/9 10:23
 * @Description:
 */
public class Java8MergeListTest {
    @Test
    public void mergeMapValuesTest() {
        Map<Integer, ListContainer> map = Maps.newHashMap();
        List<AClass> aClassList1 = Lists.newArrayList();
        AClass aClass = new AClass(1, "zhuoli1", "haha1");
        aClassList1.add(aClass);
        aClassList1.add(new AClass(2, "zhuoli2", "haha2"));
        aClassList1.add(new AClass(3, "zhuoli3", "haha3"));

        List<AClass> aClassList2 = Lists.newArrayList();
        aClassList2.add(aClass);
        aClassList2.add(new AClass(5, "zhuoli5", "haha5"));
        aClassList2.add(new AClass(6, "zhuoli6", "haha6"));

        List<Integer> aClassList2Ids = aClassList2.stream().map(AClass::getId).collect(Collectors.toList());

        /*交集*/
        /*[AClass(id=1, name=zhuoli1, description=haha1)]*/
        List<AClass> intersectResult = aClassList1.stream().filter(aClassList2::contains).collect(Collectors.toList());
        System.out.println(intersectResult);

        /*并集*/
        List<AClass> unionResult = Stream.of(aClassList1, aClassList2).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        assertEquals(unionResult.size(), 5);
        System.out.println(unionResult);

        /*差集*/
        /*[AClass(id=2, name=zhuoli2, description=haha2), AClass(id=3, name=zhuoli3, description=haha3)]*/
        List<AClass> differenceResult = aClassList1.stream().filter(x -> !aClassList2.contains(x)).collect(Collectors.toList());
        System.out.println(differenceResult);

        map.put(1, new ListContainer(aClassList1));
        map.put(2, new ListContainer(aClassList2));

        /*合并多个list*/
        List<AClass> aClassListResult = map.values().stream().flatMap(listContainer -> listContainer.getLst().stream()).collect(Collectors.toList());
        /*注意跟并集的区别*/
        assertEquals(aClassListResult.size(), 6);
        System.out.println(aClassListResult);
    }

    @Test
    public void intersectByKeyTest() {
        Data2 data2 = new Data2(10501, "JOE", "Type1");
        Data1 data1 = new Data1(10501, "JOE", 3000000);
        data2.setData1(data1);
        data1.setData2(data2);
        List<Data2> listOfData2 = new ArrayList<Data2>();
        listOfData2.add(data2);


        List<Data1> listOfData1 = new ArrayList<Data1>();

        listOfData1.add(data1);

        List<OutputData> result = listOfData1.stream()
                .flatMap(x -> listOfData2.stream()
                        .filter(y -> x.getId() == y.getId())
                        .map(y -> new OutputData(y.getId(), x.getName(), y.getType(), x.getAmount())))
                .collect(Collectors.toList());
        System.out.println(result);

        /*difference by key*/
        List<Data1> data1IntersectResult = listOfData1.stream().filter(key -> listOfData2.stream().map(Data2::getId).collect(Collectors.toList()).contains(key.getId())).collect(Collectors.toList());
        System.out.println(data1IntersectResult);
    }

    @Test
    public void flatMapToIntTest() {
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("1", "2"),
                Arrays.asList("5", "6"),
                Arrays.asList("3", "4")
        );

        IntStream intStream =
                listOfLists.stream()
                        .flatMapToInt(childList ->
                                childList.stream()
                                        .mapToInt(Integer::new));

        int sum = intStream.peek(System.out::println).sum();
        System.out.println("sum: " + sum);
    }

    @Test
    public void mapToLongTest(){
        List<Integer> integerList = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Long> longList = integerList.stream().mapToLong(x -> x).boxed().collect(Collectors.toList());
        System.out.println(longList);

        List<Double> doubleList = longList.stream().mapToDouble(x -> x).boxed().collect(Collectors.toList());
        System.out.println(doubleList);
    }
}
