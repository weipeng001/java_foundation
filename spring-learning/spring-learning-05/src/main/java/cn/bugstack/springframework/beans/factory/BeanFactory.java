package cn.bugstack.springframework.beans.factory;

import cn.bugstack.springframework.beans.BeansException;

/**
 * Bean工厂改成了接口,让后续实现者不再关心调用逻辑
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
public interface BeanFactory {

    /**
     * 提供 Bean 的获取方法 getBean(String name)，
     * 之后这个 Bean 工厂接口由抽象类 AbstractBeanFactory 实现。
     * 这样使用模板模式的设计方式，可以统一收口通用核心方法的调用逻辑和标准定义，
     * 也就很好的控制了后续的实现者不用关心调用逻辑，按照统一方式执行。
     *
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;


    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 已经存在的 Bean 工厂接口用于获取 Bean 对象，这次新增加了按照类型获取 Bean 的方法
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
