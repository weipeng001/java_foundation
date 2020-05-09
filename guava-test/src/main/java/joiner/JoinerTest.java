package joiner;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**

 * @Date: 2018/7/5 12:43
 * @Description:
 */
public class JoinerTest {
    @Test
    public void joinTest(){
        List<String> list = Lists.newArrayList("aaa", "bbb", null, "ccc");
        String joinStr = Joiner.on("-").skipNulls().join(list);
        assertEquals("aaa-bbb-ccc", joinStr);
    }

    @Test
    public void useForNullTest(){
        List<String> list = Lists.newArrayList("aaa", "bbb", null, "ccc");
        String joinStr = Joiner.on("-").useForNull("null").join(list);
        assertEquals("aaa-bbb-null-ccc", joinStr);
    }

    @Test
    public void appendToTest(){
        List<String> list = Lists.newArrayList("aaa", "bbb", null, "ccc");
        StringBuilder sb = new StringBuilder("this is: ");
        StringBuilder result = Joiner.on("-").skipNulls().appendTo(sb, list);
        assertEquals("this is: aaa-bbb-ccc", result.toString());
    }

    @Test
    public void withKeyValueSeparatorTest(){
        Map<Integer, String> idNameMap = Maps.newHashMap();
        idNameMap.put(1, "Michael");
        idNameMap.put(2, "Mary");
        idNameMap.put(3, "Jane");

        String result = Joiner.on("\n").withKeyValueSeparator(":").join(idNameMap);
        System.out.println(result);
    }
}
