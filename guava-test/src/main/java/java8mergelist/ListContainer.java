package java8mergelist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**

 * @Date: 2018/8/9 10:22
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ListContainer {

    private List<AClass> lst;
}
