package com.weipeng.dozer.demo;

import com.weipeng.dozer.dao.TargetDemo;
import com.weipeng.dozer.dao.User;
import com.weipeng.dozer.util.BeanUtils;
import com.weipeng.dozer.vo.UserDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 转换类测试
 *
 * @author wuweipeng
 * @date 2020/9/19
 */
public class TransformDemo {

    private User user = new User();

    private List<User> userList = new ArrayList<>();

    @Before
    public void init() {

        user.setId(1);
        user.setName("weipeng");
        user.setBirthday(new Date());

        TargetDemo targetDemo = new TargetDemo();
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
        UserDto userVO = BeanUtils.copy(user, UserDto.class);
        System.out.println(userVO);
    }

    @Test
    public void copyList() {
        List<UserDto> userVO = BeanUtils.copyList(userList, UserDto.class);
        System.out.println(userVO);
    }

}
