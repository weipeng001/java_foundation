package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

/**
 * Bean 定义注册表
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册 Bean的信息
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
