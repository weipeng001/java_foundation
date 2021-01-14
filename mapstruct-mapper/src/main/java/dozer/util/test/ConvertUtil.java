package dozer.util.test;

import dozer.dao.UserDO;

/**
 * @author wuweipeng
 * @date 2020/9/24 21:47
 */
public class ConvertUtil {
    // 有特殊处理的逻辑 可以自己编写工具类
    public static String specialMethod(UserDO userDO) {
        return userDO.getSpecialName() + "!@#%@#$%";
    }
}
