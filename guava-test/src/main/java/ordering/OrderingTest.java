package ordering;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.Collections;
import java.util.List;

/**

 * @Date: 2018/6/19 10:52
 * @Description:
 */
public class OrderingTest {
    class Person {
        String name;
        Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        Person(String name, Integer age) {
            super();
            this.name = name;
            this.age = age;
        }

        public Person() {
            super();
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public void orderingTest(){
        List<Person> personList = Lists.newArrayList();
        personList.add(new Person("Tom", 18));
        personList.add(new Person("Michael", 19));
        personList.add(new Person("Jane", 20));

        /*根据name自然排序构建Ordering*/
        Ordering<Person> nameOrdering = Ordering.natural().nullsFirst().onResultOf(new Function<Person, String>(){
            public String apply(Person person) {
                return person.name;
            }
        });

        List<Person> nameOrderedList = nameOrdering.sortedCopy(personList);
        System.out.println("sortedCopy by Name:" + nameOrderedList);

        /*使用sortedCopy不会改变原List顺序*/
        System.out.println("Origin List:" + personList);

        /*greatestOf抽取Top K*/
        System.out.println("Top K by Name:" + nameOrdering.greatestOf(personList, 2));
        /*leastOf抽取最小的Top K*/
        System.out.println("least Top K by Name:" + nameOrdering.leastOf(personList, 2));

        /*也可以使用迭代器抽取Top K*/
        System.out.println("Top K by Name:" + nameOrdering.greatestOf(personList.iterator(), 2));
        System.out.println("least Top K by Name:" + nameOrdering.leastOf(personList.iterator(), 2));

        /*Collections.sort会改变原List顺序*/
        Collections.sort(personList, nameOrdering);
        System.out.println("Collections.sort by Name:" + personList);

        /*Java 8之后也可以使用list.sort方法替代Collections.sort*/
        personList.sort(nameOrdering);
        System.out.println("personList.sort by Name:" + personList);

        /*根据age自然排序构建Ordering*/
        Ordering<Person> ageOrdering = Ordering.natural().nullsFirst().onResultOf(new Function<Person, Integer>(){
            public Integer apply(Person person) {
                return person.age;
            }
        });
        personList.sort(ageOrdering);
        System.out.println("age Order:" + personList);

        /*使用reverse()获取逆序ordering*/
        personList.sort(ageOrdering.reverse());
        System.out.println("age Reverse Order:" + personList);

        /*使用compound构造复合排序ordering,先按照name排序,name相同则按照age排序*/
        Ordering<Person> compoundOrdering = nameOrdering.compound(ageOrdering);
        personList.add(new Person("Michael", 25));
        personList.sort(compoundOrdering);
        System.out.println("compoundOrdering:" + personList);
    }

    public static void main(String[] args){
        OrderingTest orderingTest = new OrderingTest();
        orderingTest.orderingTest();
    }

}
