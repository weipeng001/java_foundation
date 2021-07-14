package cn.bugstack.springframework.test.bean;

/**

 */
public class UserService {

    private String uId;

    private UserDao userDao;

    public String queryUserInfo() {
        return this.userDao.queryUserName(this.uId);
    }

    public String getuId() {
        return this.uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return this.userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
