package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

/**
 * 抽象的工厂类，使用模板方法的设计模式
 * 统一通用核心方法的调用逻辑和标准定义
 * BeanDefinition注册表接口
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(final String name) throws BeansException {
        return this.doGetBean(name, null);
    }

    @Override
    public Object getBean(final String name, final Object... args) throws BeansException {
        return this.doGetBean(name, args);
    }

    protected Object doGetBean(final String name, final Object[] args) {
        final Object bean = this.getSingleton(name);
        if (bean != null) {
            return bean;
        }

        final BeanDefinition beanDefinition = this.getBeanDefinition(name);
        return this.createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
