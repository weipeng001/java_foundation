package multiset;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**

 * @Date: 2018/6/25 17:27
 * @Description:
 */
public class MultiSetTest {

    @Test
    public void multiSetTest() {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b", 2);
        List<String> addList = Lists.newArrayList("c", "c", "c");
        multiset.addAll(addList);
        /*输出 [a, b x 2, c x 3] */
        System.out.println(multiset);
        assertEquals(2, multiset.count("b"));

        assertEquals(6, multiset.size());

        assertTrue(multiset.contains("a"));

        assertTrue(multiset.containsAll(Lists.newArrayList("a", "c")));
        assertFalse(multiset.containsAll(Lists.newArrayList("a", "c", "e")));

        Set<String> set = Sets.newLinkedHashSet("a", "b", "c");
        assertEquals(set, multiset.elementSet());

        Set<Multiset.Entry<String>> setEntry = multiset.entrySet();
        setEntry.forEach(entry -> {
            System.out.println(entry.getElement() + ": " + entry.getCount());
        });

        multiset.remove("a");
        assertEquals(5, multiset.size());
        multiset.remove("b", 10);
        assertEquals(3, multiset.size());
        multiset.removeAll(Lists.newArrayList("c"));
        assertEquals(0, multiset.size());

        multiset.setCount("x", 10);
        assertEquals(10, multiset.size());

        multiset.setCount("y", 3);
        assertEquals(13, multiset.size());

        multiset.setCount("y", 3, 5);
        assertEquals(15, multiset.size());
        multiset.setCount("y", 3, 8);
        assertEquals(15, multiset.size());
    }

    public static void main(String args[]) {
    }
}
