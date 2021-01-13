import com.weipeng.dozer.dao.Target;
import com.weipeng.dozer.dao.User;
import com.weipeng.dozer.dto.UserDto2;
import com.weipeng.dozer.util.BeanUtils;
import com.weipeng.dozer.dto.UserDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author wuweipeng
 * @Date 2021/1/14 2:06
 * @Description 转换测试类
 **/
public class TransformTest {

    private User user = new User();

    private List<User> userList = new ArrayList<>();

    @Before
    public void init() {
        user.setId(1);
        user.setName("伟鹏");
        user.setBirthday(new Date());

        Target targetDemo = new Target();
        targetDemo.setId("targetId");
        targetDemo.setName("targetName");
        user.setTarget(targetDemo);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("weipeng");
            user.setBirthday(new Date());
            user.setTarget(targetDemo);
            userList.add(user);
        }
    }


    @Test
    public void copy() {
        //对象转换
        UserDto userVO = BeanUtils.copy(user, UserDto.class);
        System.out.println(userVO);

        //对象转换
        UserDto2 userDto2 = BeanUtils.copy(user, UserDto2.class);
        System.out.println(userDto2);

        // 判断是否是深拷贝
        User user1 = BeanUtils.copy(user, User.class);
        System.out.println(user1==user);//false
    }

    @Test
    public void copyList() {
        List<UserDto> userVO = BeanUtils.copyList(userList, UserDto.class);
        System.out.println(userVO);
        List<UserDto2> userVO2= BeanUtils.copyList(userList, UserDto2.class);
        System.out.println(userVO2);
    }

}
