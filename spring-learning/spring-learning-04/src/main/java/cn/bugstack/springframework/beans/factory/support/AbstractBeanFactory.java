package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition注册表接口
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return this.doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return this.doGetBean(name, args);
    }

    protected Object doGetBean(final String name, final Object[] args) {
        Object bean = this.getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = this.getBeanDefinition(name);
        return this.createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
