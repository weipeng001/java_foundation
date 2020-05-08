package com.weipeng.dozer.dao;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UserDO {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String address;
    private String password;
    private String nickName;
    private Date birthday;
    private Integer isDeleted;
}