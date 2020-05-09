package java8.sort.set;

import com.google.common.collect.Sets;
import java8.sort.list.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class SetSortExample {
    private Set<Student> studentSet = Sets.newHashSet();

    @Before
    public void init(){
        studentSet.add(new Student(1001L, "zhuoli", 99));
        studentSet.add(new Student(999L, "Alice", 87));
        studentSet.add(new Student(1345L, "Michael", 90));
        studentSet.add(new Student(1024L, "Jane", 99));
    }

    @Test
    public void setSortTest(){
        /*使用Java8 Stream order,结果转List*/
        List<Student> sortedList = studentSet.stream().sorted(Comparator.comparing(Student::getScore).reversed()).collect(Collectors.toList());
        System.out.println(sortedList);

        /*使用Java8 Stream order,使用LinkedHashSet保持顺序*/
        LinkedHashSet<Student> sortedSet1 = Sets.newLinkedHashSet();
        studentSet.stream().sorted(Comparator.comparing(Student::getScore).reversed()).forEachOrdered(sortedSet1::add);
        System.out.println(sortedSet1);
    }
}
