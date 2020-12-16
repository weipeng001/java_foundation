package java8mergelist;

import lombok.AllArgsConstructor;
import lombok.Data;

/**

 * @Date: 2018/8/9 11:01
 * @Description:
 */
@Data
@AllArgsConstructor
public class Data2 {
    private  Data1 data1;
    private int id;
    private String name;
    private String type;

    public Data2(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Data2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
