package cache;

/**
 * @Author: zhuoli
 * @Date: 2018/7/13 10:09
 * @Description:
 */
public class CacheTest {
    public static void main(String[] args) {
        Dao dao = new Dao();

        for (int i = 0; i < 3; i++) {
            System.out.println("--- " + i + " ---");
            System.out.println(dao.getList("0101"));
            System.out.println(dao.getList("0102"));
            System.out.println(dao.getList("0103"));
            System.out.println(dao.getList("0104"));
            System.out.println();
        }
    }
}
