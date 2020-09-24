package dozer.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
public class UserVO {
    private Integer id;
    private Date birthday;
    private String test;
}
