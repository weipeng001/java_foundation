package java8.sort.list;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List排序
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

    /**
     * 逆序排序规则
     */
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

    /**
     * 多字段排序---方式一
     */
    @Test
    public void testSort(){
        Comparator<Student> compareByScoreAndNameReverse = Comparator.comparing(Student::getScore).thenComparing(Student::getName);
        studentList.sort(compareByScoreAndNameReverse);
        System.out.println(studentList);
    }

    /**
     * 多字段排序---方式二
     */
    @Test
    public void testSort1(){
        Comparator<Student> compareByScoreAndNameReverse = Comparator.comparing(Student::getScore).thenComparing(Student::getName);
        Collections.sort(studentList, compareByScoreAndNameReverse);
        System.out.println(studentList);
    }

    /**
     * 自定义排序
     */
    @Test
    public void  customSort (){
        final  List<String> sortList =  Arrays.asList("Alice","Michael","Jane","zhuoli");
        List<Student> result = studentList.stream().sorted(Comparator.comparing(Student::getName, (x, y) -> {
            for(String sort : sortList){
                if(sort.equals(x) || sort.equals(y)){
                    if(x.equals(y)){
                        return 0;
                    }else if(sort.equals(x)){
                        return -1;
                    }else{
                        return 1;
                    }
                }
            }
            return 0;
        })).collect(Collectors.toList());
        System.out.println(result);
    }
}
