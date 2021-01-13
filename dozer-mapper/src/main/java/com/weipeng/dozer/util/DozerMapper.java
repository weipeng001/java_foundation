package com.weipeng.dozer.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @Author wuweipeng
 * @Date 2021/1/14 2:06
 * @Description 获取DozerBeanMapper单例
 **/
public class DozerMapper {

    private static class DozerMapperHolder {
        private static final Mapper single = new DozerBeanMapper();
    }

    public static Mapper get() {
        return DozerMapperHolder.single;
    }

}
