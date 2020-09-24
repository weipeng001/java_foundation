package dozer.demo;

import dozer.dao.UserDO;
import dozer.util.UserConvert;
import dozer.vo.UserVO;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class TransformDemo {
    private UserDO userDO = new UserDO();

    @Before
    public void init(){
        userDO.setId(1);
        userDO.setBirthday(new Date());
        userDO.setSpecialName("伟鹏");
    }

    /**
     * 如果工具类 启动异常 需要 使用maven clean install
     * maven clean install  之后 mapper会自动帮你生成一个转换类
     */
    @Test
    public void transformInCommonWay(){
        UserVO userVO = UserConvert.INSTANCE.transformation(userDO);
        System.out.println();
    }
}
