package cn.bugstack.springframework.test.bean;

/**
 * 实际业务实体
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
public class UserService {

    private String name;

    /**
     * 只有一个有 name 入参的构造函数，
     * 方便我们验证这样的对象是否能被实例化。
     *
     * @param name
     */
    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + this.name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("实际业务实体：").append(this.name);
        return sb.toString();
    }
}
