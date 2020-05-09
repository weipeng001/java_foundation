package java8groupingby;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**

 * @Date: 2018/7/30 13:28
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class BlogPost {
    private String title;
    private String author;
    private Integer type;
    private Integer likes;
}
