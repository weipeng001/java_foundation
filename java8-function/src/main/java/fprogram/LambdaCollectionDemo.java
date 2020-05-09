package fprogram;

import com.google.common.collect.Lists;
import fprogram.pojo.Person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhuoli
 * @Date: 2018/6/11 21:44
 * @Description:
 */
public class LambdaCollectionDemo {
    public void StreamOperation() {
        List<Integer> collected = Stream.of(1, 5, 8, 6, 99).collect(Collectors.toList());
        System.out.println("基础流操作：" + collected);

        List<Integer> mapList = collected.stream().map(ele -> ele + 1).collect(Collectors.toList());
        System.out.println("Map+1操作：" + mapList);

        List<Integer> filterList = collected.stream().filter(ele -> ele % 2 == 0).collect(Collectors.toList());
        System.out.println("filter筛选偶数：" + filterList);

        List flatMapList = Stream.of(mapList, filterList).flatMap(list -> list.stream()).collect(Collectors.toList());
        System.out.println("flat合并List：" + flatMapList);

        Integer maxValue = collected.stream().max((x, y) -> x.compareTo(y)).get();
        System.out.println("max查找最大值：" + maxValue);

        Integer minValue = collected.stream().min((x, y) -> x.compareTo(y)).get();
        System.out.println("min查找最小值：" + minValue);

        int reduceResult = collected.stream().reduce(1, (x, y) -> x + y);
        System.out.println("reduce获取最终值：" +reduceResult);

        int parallelResult = Stream.of("Apple", "Banana", "Orange", "Pear")
                .parallel()
                .map(String::length)
                .reduce(Integer::sum)
                .get();
        System.out.println("parallel并行流操作：" + parallelResult);

        List<Person> personList = Lists.newArrayList();
        personList.add(new Person("xiaoming", 15));
        personList.add(new Person("haha", 8));
        personList.add(new Person("dalao", 25));
        personList.add(new Person("caiji", 5));

        Person maxPerson = personList.stream().max(Person::compareTo).get();
        System.out.println("年龄最大的人：" + maxPerson.getName() + " 年龄：" + maxPerson.getAge());

    }

    public static void main(String[] args) {
        LambdaCollectionDemo lambdaCollectionDemo = new LambdaCollectionDemo();
        lambdaCollectionDemo.StreamOperation();
    }

}
