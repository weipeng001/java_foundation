package com.weipeng.dozer.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;


public class DozerMapper {

    private static class DozerMapperHolder {
        private static final Mapper single = new DozerBeanMapper();
    }

    public static Mapper get() {
        return DozerMapperHolder.single;
    }

}
