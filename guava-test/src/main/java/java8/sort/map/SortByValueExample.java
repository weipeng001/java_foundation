package java8.sort.map;

import com.google.common.collect.Lists;
import java8.sort.list.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


public class SortByValueExample {
    @Test
    public void sortByCommonValue() {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap);

        //sort by values, and reserve it, 10,9,8,7,6...
        Map<String, Integer> result = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        //Alternative way
        Map<String, Integer> result2 = new LinkedHashMap<>();
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));

        System.out.println("Sorted...");
        System.out.println(result);
        System.out.println(result2);
    }

    @Test
    public void sortedByObjectValueExample(){
        List<Student> studentList = Lists.newArrayList();
        studentList.add(new Student(1001L, "zhuoli", 99));
        studentList.add(new Student(999L, "Alice", 87));
        studentList.add(new Student(1345L, "Michael", 90));
        studentList.add(new Student(1024L, "Jane", 99));

        //List -> Map
        Map<Long, Student> studentMap = studentList.stream().collect(Collectors.toMap(Student::getId, ele -> ele));

        //sort value by student score
        Map<Long, Student> result = studentMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(Student::getScore).reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(result);
    }
}
