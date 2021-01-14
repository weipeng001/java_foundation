package dozer.util;

import dozer.dao.UserDO;
import dozer.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * source ：UserDO的属性
     * target ：UserVO的属性
     * 如果 source 和 target 属性名称一致 可忽略不写
     * expression :需要特殊处理的 可以使用工具类 如果ConvertUtil不是在当前包下 需要指定全路径类名 如 dozer.util.test.ConvertUtil
     *
     * @param user
     * @return
     */
    @Mappings({
            @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "id", target = "id"),
            @Mapping(target = "test", expression = "java(dozer.util.test.ConvertUtil.specialMethod(user))")

    })
    UserVO transformation(UserDO user);

    List<UserVO> transformation(List<UserDO> userList);
}