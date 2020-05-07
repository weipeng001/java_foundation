package com.weipeng.dozer.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class BeanUtils {

    public static <E, D> Function<E, D> copyFun(Class<D> dClass) {
        return e -> DozerMapper.get().map(e, dClass);
    }

    //复制单个对象
    public static <E, D> D copy(E e, Class<D> dClass) {
        return copyFun(dClass).apply(e);
    }


    //复制List
    public static <E, D> List<D> copyList(Collection<E> eCollection, Class<D> dClass) {
        return eCollection.stream().map(copyFun(dClass)).collect(toList());
    }

    public static <S, D> void copy(S s, D d) {
        DozerMapper.get().map(s, d);
    }

}
