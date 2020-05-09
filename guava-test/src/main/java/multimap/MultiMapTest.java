package multimap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**

 * @Date: 2018/6/26 10:37
 * @Description:
 */
public class MultiMapTest {
    @Test
    public void multiMapTest(){
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("key1", "this");
        multimap.put("key1", "is");
        multimap.put("key1", "key1List");
        System.out.println(multimap);
        assertEquals(3, multimap.size());
        List<String> key2List = Lists.newArrayList("this", "is", "key2List");
        multimap.putAll("key2", key2List);
        System.out.println(multimap);
        assertEquals(6, multimap.size());

        assertTrue(multimap.containsKey("key1"));
        assertTrue(multimap.containsValue("key2List"));

        Multiset<String> key1MultiSet = multimap.keys();
        assertEquals(3, key1MultiSet.count("key1"));
        /*对keys返回的视图操作会影响multiMap*/
        key1MultiSet.remove("key1");
        System.out.println(multimap);
        assertEquals(5, multimap.size());

        /*对keySet返回的视图操作会影响multiMap*/
        Set<String> keySet = multimap.keySet();
        assertEquals(2, keySet.size());
        keySet.remove("key1");
        System.out.println(multimap);

        multimap.remove("key2", "this");
        System.out.println(multimap);
        multimap.remove("key2","testDeleteNonExistValue");
        System.out.println(multimap);
        assertEquals(2, multimap.size());

        List<String> key3List = Lists.newArrayList("key3","is", "create");
        multimap.putAll("key3", key3List);
        multimap.removeAll("key2");
        System.out.println(multimap);
        assertEquals(3, multimap.size());

        key3List = Lists.newArrayList("new", "key3","has","been","created");
        multimap.replaceValues("key3", key3List);
        System.out.println(multimap);
        assertEquals(5, multimap.size());
        /*key2不存在，新建key2*/
        multimap.replaceValues("key2", key3List);
        System.out.println(multimap);
        assertEquals(10, multimap.size());

        List<String> valuesCollection = Lists.newArrayList(multimap.values());
        /*对values返回的视图的操作不会影响multiMap*/
        valuesCollection.removeAll(Lists.newArrayList("key3"));
        valuesCollection.add("addNew");
        System.out.println(multimap);
        assertEquals(10, multimap.size());

        multimap.clear();
        assertTrue(multimap.isEmpty());
    }

    public static void main(String args[]){
    }
}
