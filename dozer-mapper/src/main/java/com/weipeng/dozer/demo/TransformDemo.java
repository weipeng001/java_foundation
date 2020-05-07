package com.weipeng.dozer.demo;

import com.weipeng.dozer.dao.UserDO;
import com.weipeng.dozer.util.BeanUtils;
import com.weipeng.dozer.vo.UserVO;
import com.weipeng.dozer.vo.UserVO2;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

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
        UserVO userVO = new UserVO();
        userVO.setId(userDO.getId());
        userVO.setAge(userDO.getAge());
        userVO.setName(userDO.getName());
        userVO.setAddress(userDO.getAddress());
        userVO.setBirthday(userDO.getBirthday());
        userVO.setGender(userDO.getGender());
        userVO.setNickName(userDO.getNickName());
        userVO.setClickCount(99);

        System.out.println(userVO);
    }

    @Test
    public void transformInDozerWay(){
        UserVO userVO =  BeanUtils.copy(userDO, UserVO.class);
        userVO.setClickCount(99);
        System.out.println(userVO);
    }

    @Test
    public void transformInDozerWay2(){
//        UserVO2 userVO =  BeanUtils.copy(userDO, UserVO2.class);
//        userVO.setClickCount(99);
//        System.out.println(userVO);
        UserVO2 userVO =null;
        String personId = Optional.ofNullable(userVO).map(p->p.getAa().getAddress()).orElse(null);
        System.out.println();
    }
}
