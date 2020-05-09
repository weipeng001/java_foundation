package fprogram;

/**
 * @Author: zhuoli
 * @Date: 2018/6/11 21:06
 * @Description:
 */
@FunctionalInterface
public interface FunctionInterfaceDemo<T> {
    T calculation(T o1, T o2);
}
