package java8optional;

import com.google.common.collect.Lists;
import org.junit.Test;
import pojo.Modem;
import pojo.Person;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

/**

 * @Date: 2018/6/21 12:09
 * @Description:
 */
public class OptionalTest {
    @Test
    public void testIsPresent() {
        Optional<String> opt = Optional.of("zhuoli");
        assertTrue(opt.isPresent());

        opt = Optional.ofNullable(null);
        assertFalse(opt.isPresent());
    }

    @Test
    public void testIfPresent() {
        Optional<String> opt = Optional.of("zhuoli");
        opt.ifPresent(name -> System.out.println(name.length()));
    }

    @Test
    public void orElseTest() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("zhuoli");
        assertEquals("zhuoli", name);
    }

    @Test
    public void orElseGetTest() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseGet(() -> "zhuoli");
        assertEquals("zhuoli", name);
    }

    public String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }

    @Test
    public void orElseAndOrElseTestWhenOptionalValueIsNull() {
        String text = null;

        System.out.println("Using orElseGet:");
        String defaultText =
                Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Default Value", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Default Value", defaultText);
    }

    @Test
    public void orElseAndOrElseTestWhenOptionalValueIsNotNull() {
        String text = "zhuoli";

        System.out.println("Using orElseGet:");
        String defaultText =
                Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("zhuoli", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("zhuoli", defaultText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void orElseThrowTest() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseThrow(
                IllegalArgumentException::new);
    }

    @Test
    public void getTest() {
        Optional<String> opt = Optional.of("zhuoli");
        String name = opt.get();

        assertEquals("zhuoli", name);
    }

    @Test(expected = NoSuchElementException.class)
    public void getTestWhenValueIsNull() {
        Optional<String> opt = Optional.ofNullable(null);
        String name = opt.get();
    }

    @Test
    public void filterTest() {
        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
        assertTrue(is2016);
        boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
        assertFalse(is2017);
    }

    @Test
    public void FilterTest1() {
        assertTrue(Modem.priceIsInRange1(new Modem(10.0)));
        assertFalse(Modem.priceIsInRange1(new Modem(9.9)));
        assertFalse(Modem.priceIsInRange1(new Modem(null)));
        assertFalse(Modem.priceIsInRange1(new Modem(15.5)));
        assertFalse(Modem.priceIsInRange1(null));
    }

    @Test
    public void FilterTest2() {
        assertTrue(Modem.priceIsInRange2(new Modem(10.0)));
        assertFalse(Modem.priceIsInRange2(new Modem(9.9)));
        assertFalse(Modem.priceIsInRange2(new Modem(null)));
        assertFalse(Modem.priceIsInRange2(new Modem(15.5)));
        assertFalse(Modem.priceIsInRange2(null));
    }

    @Test
    public void mapTest() {
        List<String> companyNames = Lists.newArrayList(
                "paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);

        int size = listOptional
                .map(List::size)
                .orElse(0);
        assertEquals(6, size);
    }

    @Test
    public void filterMapTest() {
        String password = " password ";
        Optional<String> passOpt = Optional.of(password);
        boolean correctPassword = passOpt.filter(
                pass -> pass.equals("password")).isPresent();
        assertFalse(correctPassword);

        correctPassword = passOpt
                .map(String::trim)
                .filter(pass -> pass.equals("password"))
                .isPresent();
        assertTrue(correctPassword);
    }

    @Test
    public void filterMapTest1() {
        Person person = new Person("zhuoli",18,"123");
        Optional<Person> personOptional = Optional.of(person);

        Optional<Optional<String>> nameOptionalWrapper
                /*Person::getName返回Optional<String>，map方法嵌套Optional<String>对象返回*/
                = personOptional.map(Person::getName);
        Optional<String> nameOptional
                = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
        String name1 = nameOptional.orElse("");
        assertEquals("zhuoli", name1);

        String name = personOptional
                /*Person::getName返回Optional<String>，flatMap直接将Optional<String>对象返回*/
                .flatMap(Person::getName)
                .orElse("");
        assertEquals("zhuoli", name);
    }

    public static void main(String[] args) {
        Modem modem = new Modem(null);
        Optional<Modem> optionalModem = Optional.ofNullable(modem);
        int price = optionalModem.map(Modem::getPrice).map(Double::intValue).orElse(0);
        System.out.println(price);
    }
}
