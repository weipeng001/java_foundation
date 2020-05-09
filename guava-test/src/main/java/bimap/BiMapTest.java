package bimap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class BiMapTest {
    @Test
    public void biMapTest(){
        BiMap<Integer, String> idNameBiMap = HashBiMap.create();
        idNameBiMap.put(1, "Michael");
        idNameBiMap.put(2, "Jane");
        idNameBiMap.put(3, "Marry");
        /*put添加已存在的value会报IllegalArgumentException*/
        assertThatThrownBy(()->idNameBiMap.put(4, "Marry"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasNoCause();

        /*put添加已存在的key，会覆盖之前key对应的value值*/
        idNameBiMap.put(3,"zhuoli");
//        {1=Michael, 2=Jane, 3=zhuoli}
        System.out.println(idNameBiMap);
        assertEquals(3, idNameBiMap.size());

        /*forcePut强制插入已存在的valu，会删除之前的键值对 2 - Jane*/
        idNameBiMap.forcePut(4, "Jane");
//        {1=Michael, 3=zhuoli, 4=Jane}
        System.out.println(idNameBiMap);
        assertEquals(3, idNameBiMap.size());

        Set<String> biMapValuesSet = idNameBiMap.values();
//        [Michael, zhuoli, Jane]
        System.out.println(biMapValuesSet);

        BiMap<String, Integer> nameIdBiMap = idNameBiMap.inverse();
//        {Michael=1, zhuoli=3, Jane=4}
        System.out.println(nameIdBiMap);
        assertEquals(4, nameIdBiMap.get("Jane").intValue());
    }
}
