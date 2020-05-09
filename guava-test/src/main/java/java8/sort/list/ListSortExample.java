package java8.sort.list;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**

 * @Date: 2018/7/31 10:13
 * @Description:
 */
public class ListSortExample {

    private List<Student> studentList = Lists.newArrayList();

    @Before
    public void init(){
        studentList.add(new Student(1001L, "zhuoli", 99));
        studentList.add(new Student(999L, "Alice", 87));
        studentList.add(new Student(1345L, "Michael", 90));
        studentList.add(new Student(1024L, "Jane", 99));
    }

    @Test
    public void testSortWithStream(){

        /*使用Java8 Stream order*/
        List<Student> sortedList = studentList.stream().sorted(Comparator.comparing(Student::getScore).reversed()).collect(Collectors.toList());
        System.out.println(studentList);
        System.out.println(sortedList);

        /*使用Java8 Stream order按照score、name逆序排序*/
        List<Student> sortedList1 = studentList.stream().sorted(Comparator.comparing(Student::getScore).thenComparing(Student::getName).reversed()).collect(Collectors.toList());
        System.out.println(sortedList1);
    }

    @Test
    public void testSort(){
        Comparator<Student> compareByScoreAndNameReverse = Comparator.comparing(Student::getScore).thenComparing(Student::getName).reversed();
        studentList.sort(compareByScoreAndNameReverse);
        System.out.println(studentList);
    }

    @Test
    public void testSort1(){
        Comparator<Student> compareByScoreAndNameReverse = Comparator.comparing(Student::getScore).thenComparing(Student::getName).reversed();
        Collections.sort(studentList, compareByScoreAndNameReverse);
        System.out.println(studentList);
    }
}
