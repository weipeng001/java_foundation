package java8mergelist;

import lombok.AllArgsConstructor;
import lombok.Data;

/**

 * @Date: 2018/8/9 11:00
 * @Description:
 */
@Data
@AllArgsConstructor
public class Data1 {
    private int id;
    private String name;
    private int amount;
    private  Data2 data2;

    public Data1(int id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Data1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
