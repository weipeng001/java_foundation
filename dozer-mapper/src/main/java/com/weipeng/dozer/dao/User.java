package com.weipeng.dozer.dao;

import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
public class User {

    private Integer id;

    private String name;

    private Date birthday;

    private Target target;


}
