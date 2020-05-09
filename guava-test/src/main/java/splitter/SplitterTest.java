package splitter;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**

 * @Date: 2018/7/6 11:17
 * @Description:
 */
public class SplitterTest {
    @Test
    public void splitStringToIterableWithDelimiter() {
        /*通过Char初始化拆分器，将String分隔为Iterable*/
        String str = "this, is  , , random , text,";
        List<String> result = Lists.newArrayList(Splitter.on(',').omitEmptyStrings().trimResults().split(str));
        assertThat(result, contains("this", "is", "random", "text"));

        String str1 = "~?~this, is~~ , , random , text,";
        result = Splitter.on(',').omitEmptyStrings().trimResults(CharMatcher.anyOf("~? ")).splitToList(str1);
        System.out.println(result);
        assertThat(result, contains("this", "is", "random", "text"));
    }

    @Test
    public void splitStringToListWithDelimiter() {
        /*通过Char初始化拆分器，将String直接分隔为List*/
        String str = "this, is  , , random , text,";
        List<String> result = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(str);
        assertThat(result, contains("this", "is", "random", "text"));

        /*生成的list不支持add、remove操作*/
        assertThatThrownBy(() -> result.add("haha"))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasNoCause();
    }

    @Test
    public void splitStringToListWithCharMatcher() {
        /*通过CharMatcher初始化拆分器*/
        String str = "a,b;c.d,e.f),g,h.i;j.1,2.3;";

        List<String> result = Splitter.on(CharMatcher.anyOf(";,.)")).omitEmptyStrings().trimResults().splitToList(str);
        assertEquals(13, result.size());
    }

    @Test
    public void splitStringToListWithRegularExpression() {
        /*通过正则表达式初始化拆分器*/
        String str = "apple.banana,,orange,,.";

        List<String> result = Splitter.onPattern("[.|,]").omitEmptyStrings().trimResults().splitToList(str);
        assertEquals(3, result.size());
    }

    @Test
    public void splitStringToListWithFixedLength() {
        /*将字符串分割为元素长度固定的List，最后一个元素长度不足可以直接返回*/
        String str = "Hello world";
        List<String> result = Splitter.fixedLength(3).splitToList(str);

        assertThat(result, contains("Hel", "lo", "wor", "ld"));
    }

    @Test
    public void splitStringToMap() {
        /*String转Map*/
        String str = "John=first,Adam=second";
        Map<String, String> result = Splitter.on(",")
                .withKeyValueSeparator("=")
                .split(str);

        assertEquals("first", result.get("John"));
        assertEquals("second", result.get("Adam"));
    }
}
