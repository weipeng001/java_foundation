package com.weipeng.dozer.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: zhuoli
 * @Date: 2018/9/30 16:22
 * @Description:
 */
@Data
@ToString
public class UserVO {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String address;
    private String nickName;
    private Date birthday;
    private Integer clickCount;
}
