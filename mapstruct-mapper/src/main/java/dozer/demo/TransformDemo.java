package dozer.demo;

import dozer.dao.UserDO;
import dozer.util.wewe;
import dozer.vo.UserVO;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TransformDemo {
    private UserDO userDO = new UserDO();

    @Before
    public void init(){
        userDO.setId(1);
        userDO.setAge(18);
        userDO.setName("weipeng");
        userDO.setAddress("shenzhen");
        userDO.setBirthday(Date.from(LocalDate.of(2000, 1, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        userDO.setGender("male");
        userDO.setNickName("吴伟鹏");
        userDO.setPassword("123456");
        userDO.setIsDeleted(0);
    }

    @Test
    public void transformInCommonWay(){
        UserVO userVO = wewe.INSTANCE.h2(userDO);
        System.out.println();
    }
}
