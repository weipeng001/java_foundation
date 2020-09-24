package dozer.dao;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UserDO {
    private Integer id;
    private Date birthday;
    private String specialName;
}
