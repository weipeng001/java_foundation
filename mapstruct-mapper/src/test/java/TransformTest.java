import dozer.dao.UserDO;
import dozer.util.UserConvert;
import dozer.vo.UserVO;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 *
 * @author wuweipeng
 * @date 2021/1/14
 * @Description  转换测试类
 */
public class TransformTest {
    private UserDO userDO = new UserDO();

    @Before
    public void init(){
        userDO.setId(1);
        userDO.setBirthday(new Date());
        userDO.setSpecialName("伟鹏");
    }

    /**
     * 如果工具类 启动异常 需要 使用maven clean install
     * maven clean install  之后 mapper会自动帮你生成一个接口实现类
     */
    @Test
    public void copy(){
        UserVO userVO = UserConvert.INSTANCE.transformation(userDO);
        System.out.println();
    }
}
