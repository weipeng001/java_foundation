package pojo;

import java.util.Optional;

/**

 * @Date: 2018/6/21 13:50
 * @Description:
 */
public class Person {
    private String name;
    private int age;
    private String password;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person() {
    }

    public Person(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
}
