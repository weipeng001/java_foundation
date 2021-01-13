package com.weipeng.dozer.dto;

import lombok.Data;
import lombok.ToString;
import org.dozer.Mapping;

import java.util.Date;


@Data
@ToString
public class UserDto {

    /**
     * 如果名称一致 @Mapping 注解可以省略
     */
    @Mapping("id")
    private Integer id;

    private String name;

    private Date birthday;

    @Mapping("target.id")
    private String targetId;

    @Mapping("target.name")
    private String targetName;

    /**
     * 多余字段  不会映射
     */
    private String teacher;

}
