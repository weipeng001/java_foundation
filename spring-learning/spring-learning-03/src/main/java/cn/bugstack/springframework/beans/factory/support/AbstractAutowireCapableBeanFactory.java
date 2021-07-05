package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import lombok.Data;

import java.lang.reflect.Constructor;

/**
 * 创建策略调用
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
@Data
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 暂时写死通过 cglib的策略方式来实现实例化
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(final String beanName, final BeanDefinition beanDefinition, final Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = this.createBeanInstance(beanDefinition, beanName, args);
        } catch (final Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        this.addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 在这个方法中需要注意 Constructor 代表了你有多少个构造函数，
     * 通过 beanClass.getDeclaredConstructors()
     * 方式可以获取到你所有的构造函数，是一个集合。
     *
     * @return
     */
    protected Object createBeanInstance(final BeanDefinition beanDefinition, final String beanName, final Object[] args) {
        Constructor constructorToUse = null;
        //获取bean的class字节码
        final Class<?> beanClass = beanDefinition.getBeanClass();
        //通过字节码 获取当前类的所有构造方法
        final Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (final Constructor ctor : declaredConstructors) {
            //找到指定 想要的构造方法
            //TODO 还可以优化 如果找不到指定的构造方法应该报错
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        // 通过策略化模式 实现bean对象的实例化
        return this.getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

}
