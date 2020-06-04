package dozer.util;

import dozer.dao.UserDO;
import dozer.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface wewe {

    wewe INSTANCE = Mappers.getMapper(wewe.class);

    @Mappings({})
    UserVO h2(UserDO broker);

    List<UserVO> h2(List<UserDO> personList);
}