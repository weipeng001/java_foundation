package com.weipeng.dozer.vo;

import lombok.Data;
import lombok.ToString;
import org.dozer.Mapping;

import java.util.Date;

@Data
@ToString
public class UserVO2 {

    @Mapping("id")
    private Integer xxxxid;

    @Mapping("name")
    private String xxxxname;

    @Mapping("age")
    private Integer xxxxage;

    @Mapping("gender")
    private String xxxxgender;

    //如果是对象的话  只需要使用  @Mapping("incomeBill.price") 即可映射
    private String address;
    private String nickName;
    private Date birthday;
    private Integer clickCount;

    private UserVO aa;
}
