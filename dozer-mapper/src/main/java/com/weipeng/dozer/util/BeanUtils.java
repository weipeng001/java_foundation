package com.weipeng.dozer.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * bean工具转换类
 *
 * @author wuweipeng
 * @date 2020/9/19
 */
public class BeanUtils {

    /**
     * 复制单个对象
     * 用法参考 ：com.weipeng.dozer.demo.TransformDemo#copy()
     */
    public static <E, D> D copy(E e, Class<D> dClass) {
        return copyFun(dClass).apply(e);
    }


    /**
     * 复制 对象 集合
     * 用法参考 ：com.weipeng.dozer.demo.TransformDemo#copyList()
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
