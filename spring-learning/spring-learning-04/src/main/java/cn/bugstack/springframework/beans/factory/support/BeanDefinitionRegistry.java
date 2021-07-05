package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

/**
 * 注册表
 * @author wuweipeng
 * @date 2021/6/29
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}