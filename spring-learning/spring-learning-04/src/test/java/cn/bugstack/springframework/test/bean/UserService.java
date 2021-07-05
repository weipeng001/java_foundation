package cn.bugstack.springframework.test.bean;

import lombok.Data;

/**
 * @author wuweipeng
 * @date 2021/6/29
 */

@Data
public class UserService {

    private String uId;

    private UserDao userDao;

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + this.userDao.queryUserName(this.uId));
    }

}
