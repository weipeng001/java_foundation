package lombok.test;

import com.google.common.collect.Sets;
import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;



public class LombokTest {
    @Test
    public void builderTest(){
        BuilderExample builderExample = BuilderExample.builder().name("zhuoli").age(22).occupation("haha").build();
        assertThat(builderExample.getOccupations(), containsInAnyOrder("haha"));

        BuilderExample builderExample1 = BuilderExample.builder().occupations(Sets.newHashSet("this", "is", "builder")).build();
        assertThat(builderExample1.getOccupations(), containsInAnyOrder("this", "is", "builder"));
    }
}
