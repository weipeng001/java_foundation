package java8partitionby;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**

 * @Date: 2018/8/1 21:27
 * @Description:
 */
public class partitionByTest {
    @Test
    public void partitionByTest(){
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

        Map<Boolean, List<Integer>> groups =
                intList.stream().collect(Collectors.partitioningBy(s -> s > 6));
        List<List<Integer>> subSets = new ArrayList<List<Integer>>(groups.values());

        List<Integer> lastPartition = subSets.get(1);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
        assertThat(subSets.size(), equalTo(2));
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }
}
