package com.weipeng.dozer.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @Author wuweipeng
 * @Date 2021/1/14 2:07
 * @Description bean工具转换类
 **/
public class BeanUtils {

    /**
     *
     * 对象复制
     * 注意点 ： e 不能为null
     * 用法参考：TransformTest#copy()
     */
    public static <E, D> D copy(E e, Class<D> dClass) {
        return copyFun(dClass).apply(e);
    }

    /**
     * 复制 对象 集合
     * 注意点 ： eCollection 不能为null
     * 用法参考： TransformTest#copyList()
     */
    public static <E, D> List<D> copyList(Collection<E> eCollection, Class<D> dClass) {
        return eCollection.stream().map(copyFun(dClass)).collect(toList());
    }


    /**
     *
     * @param dClass
     * @param <E>
     * @param <D>
     * @return
     */
    public static <E, D> Function<E, D> copyFun(Class<D> dClass) {
        return e -> DozerMapper.get().map(e, dClass);
    }

}
